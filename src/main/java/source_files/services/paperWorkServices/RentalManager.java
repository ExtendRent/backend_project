package source_files.services.paperWorkServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.DTO.paperWorkDTOs.ShowRentalResponse;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.paperworkRequests.RentalRequests.CreateRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ShowRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.UpdateRentalRequest;
import source_files.exception.ValidationException;
import source_files.services.BusinessRules.paperWork.RentalBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountEntityService;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;
import source_files.services.paperWorkServices.abstracts.PaymentService;
import source_files.services.paperWorkServices.abstracts.RentalService;
import source_files.services.systemServices.SysPaymentDetailsService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.vehicleService.abstracts.CarService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus.IN_USE;
import static source_files.data.enums.types.itemTypes.ItemType.RENTAL;
import static source_files.exception.exceptionTypes.ValidationExceptionType.VALIDATION_EXCEPTION;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {

    private final RentalEntityService entityService;
    private final ModelMapperService mapper;
    private final CarService carService;
    private final SysPaymentDetailsService sysPaymentDetailsService;
    private final CustomerService customerService;
    private final DiscountEntityService discountEntityService;
    private final RentalBusinessRules rules;
    private final PaymentService paymentService;


    @Override
    public ShowRentalResponse showRentalDetails(ShowRentalRequest showRentalRequest) {
        if (carService.isCarAvailableBetween(showRentalRequest.getCarEntityId(),
                showRentalRequest.getStartDate(), showRentalRequest.getEndDate())
        ) {
            return convertToShowRentalResponse(rules.checkShowRentalRequest(showRentalRequest));
        }
        throw new ValidationException(VALIDATION_EXCEPTION, "Araç bu tarihler arasında kiralanamaz");
    }

    @Override
    public void create(CreateRentalRequest createRentalRequest) {

        RentalEntity rentalEntity = mapper.forRequest()
                .map(rules.checkCreateRentalRequest(
                                rules.fixCreateRentalRequest(createRentalRequest))
                        , RentalEntity.class);

        String discountCode = createRentalRequest.getDiscountCode();

        if (rules.checkDiscountCodeIsNull(discountCode)) {
            rentalEntity.setDiscountEntity(discountEntityService
                    .getByDiscountCode(discountCode)
            );
        }

        rentalEntity.setStartKilometer(carService.getById(createRentalRequest.getCarEntityId()).getKilometer());
        rentalEntity.setItemType(RENTAL);


        entityService.create(rentalEntity);
        try {
            rentalEntity.setPaymentDetailsEntity(paymentService.pay(createRentalRequest, rentalEntity));
            this.entityService.update(rentalEntity);
            carService.addRental(createRentalRequest.getCarEntityId(), rentalEntity);
        } catch (Exception e) {
            this.softDelete(rentalEntity.getId());
            carService.removeRental(createRentalRequest.getCarEntityId(), rentalEntity);
        }
    }

    @Override
    public RentalDTO returnCar(ReturnRentalRequest returnRentalRequest) {
        // ceza işlemleri , indirim işlemleri iptali kontrol edilecek sonuçta da totalPrice güncelleme

        RentalEntity rentalEntity = entityService.getById(returnRentalRequest.getId());
        CarEntity carEntity = rentalEntity.getCarEntity();

        rentalEntity.setPaymentDetailsEntity(sysPaymentDetailsService.update(
                rules.updatePaymentDetailsToFinal(returnRentalRequest)));

        rentalEntity.setEndKilometer(returnRentalRequest.getEndKilometer());
        rentalEntity.setActive(false);
        carEntity.setKilometer(rentalEntity.getEndKilometer());
        if (rentalEntity.getCarEntity() != null) {
            carService.removeRental(carEntity.getId(), rentalEntity);
        }

        return mapper.forResponse().map(entityService.update(rentalEntity), RentalDTO.class);
    }

    @Override
    public RentalDTO startRental(int rentalId) {
        RentalEntity rentalEntity = entityService.getById(rentalId);
        CarEntity carEntity = rentalEntity.getCarEntity();
        rentalEntity.setStartKilometer(carEntity.getKilometer());
        carService.changeStatus(carEntity, IN_USE);
        return mapToDTO(entityService.update(rentalEntity));
    }

    @Override
    public List<RentalDTO> getAllByCustomerId(int customerId) {
        return rules.checkDataList(entityService.getAllByCustomerId(customerId)).stream().map(
                rentalEntity -> mapper.forResponse().map(rentalEntity, RentalDTO.class)
        ).toList();
    }

    @Override
    public RentalDTO update(UpdateRentalRequest updateRentalRequest) {
        RentalEntity rentalEntity = mapper.forRequest().map(updateRentalRequest, RentalEntity.class);
        rentalEntity.setPaymentDetailsEntity(sysPaymentDetailsService.getById(
                rentalEntity.getPaymentDetailsEntity().getId()));
        return mapToDTO(entityService.update(rentalEntity));
    }

    @Override
    public RentalDTO getById(int id) {
        return mapper.forResponse().map(entityService.getById(id), RentalDTO.class);
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            entityService.delete(entityService.getById(id));
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        RentalEntity rentalEntity = entityService.getById(id);
        rentalEntity.setIsDeleted(true);
        rentalEntity.setDeletedAt(LocalDateTime.now());
        entityService.update(rentalEntity);
    }

    @Override
    public List<RentalDTO> getAll() {
        return rules.checkDataList(entityService.getAll()).stream()
                .map(rentalEntity -> mapper.forResponse().map(rentalEntity, RentalDTO.class)).toList();
    }

    @Override
    public List<RentalDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(entityService.getAllByDeletedState(isDeleted)).stream()
                .map(rentalEntity -> mapper.forResponse()
                        .map(rentalEntity, RentalDTO.class)).toList();
    }

    @Override //Tarihler arasında çakışan ve aktif olan rental kayıtları.
    public List<RentalDTO> getAllOverlappingRentals(LocalDate startDate, LocalDate endDate) {
        return rules.checkDataList(entityService.getAllOverlappingRentals(startDate, endDate)).stream().map(rentalEntity ->
                mapper.forResponse().map(rentalEntity, RentalDTO.class)).toList();
    }

    //--------------------------------Local Methods--------------------------------

    private RentalDTO mapToDTO(RentalEntity rentalEntity) {
        return mapper.forResponse().map(rentalEntity, RentalDTO.class);
    }

    private ShowRentalResponse convertToShowRentalResponse(ShowRentalRequest showRentalRequest) {
        // indirim işlemleri sonucu totalPrice hesaplama
        return ShowRentalResponse.builder()
                .amount(rules.calculateAmount(showRentalRequest))
                .customerDTO(customerService.getById(showRentalRequest.getCustomerEntityId()))
                .carDTO(carService.getById(showRentalRequest.getCarEntityId()))
                .discountCode(showRentalRequest.getDiscountCode())
                .startDate(showRentalRequest.getStartDate())
                .endDate(showRentalRequest.getEndDate())
                .build();
    }

    private UpdateRentalRequest convertToUpdateRequest(RentalEntity rentalEntity) {
        return UpdateRentalRequest.builder()
                .id(rentalEntity.getId())
                .carEntityId(rentalEntity.getCarEntity().getId())
                .customerEntityId(rentalEntity.getCustomerEntity().getId())
                .startKilometer(rentalEntity.getStartKilometer())
                .endDate(rentalEntity.getEndDate())
                .startDate(rentalEntity.getStartDate())
                .build();
    }

}
