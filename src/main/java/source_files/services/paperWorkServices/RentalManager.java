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
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.exception.ValidationException;
import source_files.services.BusinessRules.paperWork.RentalBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountEntityService;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;
import source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers.VehicleStatusEntityManager;
import source_files.services.paperWorkServices.abstracts.RentalService;
import source_files.services.systemServices.SysPaymentDetailsService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.vehicleService.abstracts.CarService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static source_files.data.Status.DefaultVehicleStatus.IN_USE;
import static source_files.data.types.itemTypes.ItemType.RENTAL;
import static source_files.exception.exceptionTypes.ValidationExceptionType.VALIDATION_EXCEPTION;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {

    private final RentalEntityService rentalEntityService;
    private final ModelMapperService mapper;
    private final CarService carService;
    private final SysPaymentDetailsService sysPaymentDetailsService;
    private final CustomerService customerService;
    private final DiscountEntityService discountEntityService;
    private final RentalBusinessRules rules;
    private final VehicleStatusEntityManager vehicleStatusManager;


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
        // indirim işlemleri sonucu totalPrice hesaplama

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

        rentalEntity.setPaymentDetailsEntity(
                sysPaymentDetailsService.getById(createRentalRequest.getPaymentDetailsDTO().getId())
        );

        rentalEntity.setStartKilometer(carService.getById(createRentalRequest.getCarEntityId()).getKilometer());
        rentalEntity.setItemType(RENTAL);

        UpdateCarRequest updateCarRequest =
                carService.convertToUpdateRequest(createRentalRequest.getCarEntityId());

        updateCarRequest.setVehicleStatusEntityId(vehicleStatusManager.getByStatus(IN_USE).getId());
        carService.update(updateCarRequest);
        rentalEntityService.create(rentalEntity);

    }

    @Override
    public RentalDTO returnCar(ReturnRentalRequest returnRentalRequest) {
        // ceza işlemleri , indirim işlemleri iptali kontrol edilecek sonuçta da totalPrice güncelleme

        RentalEntity rentalEntity = rentalEntityService.getById(returnRentalRequest.getRentalEntityId());

        rentalEntity.setPaymentDetailsEntity(sysPaymentDetailsService.update(
                rules.createUpdatePaymentDetailsRequest(returnRentalRequest)));

        rentalEntity.setActive(false);

        if (rentalEntity.getCarEntity() != null) {
            CarEntity carEntity = rentalEntity.getCarEntity();
            carEntity.setKilometer(rentalEntity.getEndKilometer());
            carEntity.getRentalList().remove(rentalEntity);
            carService.update(carEntity.convertToUpdateRequest());
        }

        return mapper.forResponse().map(rentalEntityService.update(rentalEntity), RentalDTO.class);
    }

    @Override
    public RentalDTO update(UpdateRentalRequest updateRentalRequest) {
        return null;
    }

    @Override
    public RentalDTO getById(int id) {
        return mapper.forResponse().map(rentalEntityService.getById(id), RentalDTO.class);
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            rentalEntityService.delete(rentalEntityService.getById(id));
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        RentalEntity rentalEntity = rentalEntityService.getById(id);
        rentalEntity.setIsDeleted(true);
        rentalEntity.setDeletedAt(LocalDateTime.now());
        rentalEntityService.update(rentalEntity);
    }

    @Override
    public List<RentalDTO> getAll() {
        return rentalEntityService.getAll().stream()
                .map(rentalEntity -> mapper.forResponse().map(rentalEntity, RentalDTO.class)).toList();
    }

    @Override
    public List<RentalDTO> getAllByDeletedState(boolean isDeleted) {
        return rentalEntityService.getAllByDeletedState(isDeleted).stream()
                .map(rentalEntity -> mapper.forResponse()
                        .map(rentalEntity, RentalDTO.class)).toList();
    }

    @Override //Tarihler arasında çakışan ve aktif olan rental kayıtları.
    public List<RentalDTO> getAllOverlappingRentals(LocalDate startDate, LocalDate endDate) {
        return rentalEntityService.getAllOverlappingRentals(startDate, endDate).stream().map(rentalEntity ->
                mapper.forResponse().map(rentalEntity, RentalDTO.class)).toList();
    }


    public ShowRentalResponse convertToShowRentalResponse(ShowRentalRequest showRentalRequest) {

        return ShowRentalResponse.builder()
                .amount(rules.calculateAmount(showRentalRequest))
                .customerDTO(customerService.getById(showRentalRequest.getCustomerEntityId()))
                .carDTO(carService.getById(showRentalRequest.getCarEntityId()))
                .discountCode(showRentalRequest.getDiscountCode())
                .startDate(showRentalRequest.getStartDate())
                .endDate(showRentalRequest.getEndDate())
                .build();
    }
}
