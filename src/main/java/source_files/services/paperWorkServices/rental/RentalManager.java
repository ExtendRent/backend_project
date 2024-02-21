package source_files.services.paperWorkServices.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.DTO.paperWorkDTOs.ShowRentalResponse;
import source_files.data.enums.defaultDataEnums.DefaultDiscount;
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
import source_files.services.paperWorkServices.abstracts.RentalStatusService;
import source_files.services.systemServices.SysPaymentDetailsService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.vehicleService.abstracts.CarService;

import java.time.LocalDateTime;
import java.util.List;

import static source_files.data.enums.defaultDataEnums.Status.DefaultRentalStatus.*;
import static source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus.IN_USE;
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
    private final RentalStatusService rentalStatusService;

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

        if (rules.discountCodeIsNotNull(discountCode)) {
            rentalEntity.setDiscountEntity(discountEntityService
                    .getByDiscountCode(discountCode)
            );
        } else {
            rentalEntity.setDiscountEntity(discountEntityService.getByDiscountCode(DefaultDiscount.NONE.name()));
        }

        rentalEntity.setStartKilometer(carService.getById(createRentalRequest.getCarEntityId()).getKilometer());
        rentalEntity.setRentalStatusEntity(rentalStatusService.getByStatus(WAITING));
        entityService.create(rentalEntity);
        try {
            rentalEntity.setPaymentDetailsEntity(paymentService.pay(createRentalRequest, rentalEntity));
            entityService.update(rentalEntity);
            carService.addRental(createRentalRequest.getCarEntityId(), rentalEntity);
            customerService.addRental(createRentalRequest.getCustomerEntityId(), rentalEntity);
        } catch (Exception e) {
            this.softDelete(rentalEntity.getId());
            carService.removeRental(createRentalRequest.getCarEntityId(), rentalEntity);
            customerService.removeRental(createRentalRequest.getCustomerEntityId(), rentalEntity);
        }
    }

    @Override
    public RentalDTO returnCar(ReturnRentalRequest returnRentalRequest) {
        // ceza işlemleri , indirim işlemleri iptali kontrol edilecek sonuçta da totalPrice güncelleme

        RentalEntity rentalEntity = this.returnReqeustToEntity(returnRentalRequest);
        CarEntity carEntity = rentalEntity.getCarEntity();

        carEntity.setKilometer(rentalEntity.getEndKilometer());
        if (rentalEntity.getCarEntity() != null) {
            carService.removeRental(carEntity.getId(), rentalEntity);
        }

        return mapToDTO(entityService.update(rentalEntity));
    }

    @Override
    public RentalDTO startRental(int rentalId) {
        RentalEntity rentalEntity = entityService.getById(rentalId);
        CarEntity carEntity = rentalEntity.getCarEntity();
        rentalEntity.setStartKilometer(carEntity.getKilometer());
        rentalEntity.setRentalStatusEntity(rentalStatusService.getByStatus(ACTIVE));
        carService.changeStatus(carEntity, IN_USE);
        return mapToDTO(entityService.update(rentalEntity));
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
        List<RentalDTO> rentalDTOList = entityService.getAllByDeletedState(isDeleted).stream()
                .map(this::mapToDTO).toList();
        rules.checkDataList(rentalDTOList);
        return rentalDTOList;
    }

    @Override
    public List<RentalDTO> getAllByStatus(int statusId) {
        List<RentalDTO> rentalDTOList = entityService.getAllByStatus(statusId).stream()
                .map(this::mapToDTO).toList();
        List<RentalDTO> collectList = rentalDTOList.stream().filter(RentalDTO::isActive).toList();
        rules.checkDataList(collectList);
        return collectList;
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return entityService.getCountByDeletedState(isDeleted);
    }

    @Override
    public int getCountByStatusId(int statusId) {
        return entityService.getCountByStatusId(statusId);
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

    private RentalEntity returnReqeustToEntity(ReturnRentalRequest returnRentalRequest) {
        RentalEntity rentalEntity = entityService.getById(returnRentalRequest.getId());
        rentalEntity.setPaymentDetailsEntity(sysPaymentDetailsService.update(
                rules.updatePaymentDetailsToFinal(returnRentalRequest)));

        rentalEntity.setEndKilometer(returnRentalRequest.getEndKilometer());
        rentalEntity.setActive(false);
        rentalEntity.setRentalStatusEntity(rentalStatusService.getByStatus(FINISHED));
        rentalEntity.setReturnDate(returnRentalRequest.getReturnDate());
        return rentalEntity;
    }

}
