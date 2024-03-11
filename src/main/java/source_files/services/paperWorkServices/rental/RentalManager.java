package source_files.services.paperWorkServices.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;
import source_files.services.paperWorkServices.abstracts.PaymentService;
import source_files.services.paperWorkServices.abstracts.RentalService;
import source_files.services.paperWorkServices.abstracts.RentalStatusService;
import source_files.services.systemServices.SysPaymentDetailsService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.vehicleService.abstracts.CarService;

import java.time.LocalDateTime;
import java.util.List;

import static source_files.data.enums.defaultDataEnums.Status.DefaultRentalStatus.ACTIVE;
import static source_files.data.enums.defaultDataEnums.Status.DefaultRentalStatus.FINISHED;
import static source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus.IN_USE;
import static source_files.exception.exceptionTypes.ValidationExceptionType.VALIDATION_EXCEPTION;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {

    private final RentalEntityService entityService;
    private final CarService carService;
    private final SysPaymentDetailsService sysPaymentDetailsService;
    private final CustomerService customerService;
    private final RentalBusinessRules rules;
    private final PaymentService paymentService;
    private final RentalStatusService rentalStatusService;

    @Override
    public ShowRentalResponse showRentalDetails(ShowRentalRequest showRentalRequest) {
        rules.checkShowRentalRequest(showRentalRequest);
        if (carService.isCarAvailableBetween(showRentalRequest.getCarEntityId(),
                showRentalRequest.getStartDate(), showRentalRequest.getEndDate())
        ) {
            return convertToShowRentalResponse(showRentalRequest);
        }
        throw new ValidationException(VALIDATION_EXCEPTION, "Araç bu tarihler arasında kiralanamaz");
    }

    @Override
    public void create(CreateRentalRequest createRentalRequest) {
        createRentalRequest = rules.fixCreateRentalRequest(createRentalRequest);
        rules.checkCreateRentalRequest(createRentalRequest);
        String discountCode = createRentalRequest.getDiscountCode();

        if (!rules.discountCodeIsNotNull(discountCode)) {
            discountCode = DefaultDiscount.NONE.name();
        }
        createRentalRequest.setDiscountCode(discountCode);

        RentalEntity rentalEntity = entityService.create(createRentalRequest);
        try {
            rentalEntity.setPaymentDetailsEntity(paymentService.pay(createRentalRequest, rentalEntity));
            entityService.update(rentalEntity);
            carService.addRental(createRentalRequest.getCarEntityId(), rentalEntity);
            customerService.addRental(createRentalRequest.getCustomerEntityId(), rentalEntity);
        } catch (Exception e) {
            this.softDelete(rentalEntity.getId());
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

        return entityService.update(rentalEntity).toModel();
    }

    @Override
    public RentalDTO startRental(int rentalId) {
        RentalEntity rentalEntity = entityService.getById(rentalId);
        CarEntity carEntity = rentalEntity.getCarEntity();
        rentalEntity.setStartKilometer(carEntity.getKilometer());
        rentalEntity.setRentalStatusEntity(rentalStatusService.getByStatus(ACTIVE));
        carService.changeStatus(carEntity, IN_USE);
        return entityService.update(rentalEntity).toModel();
    }

    @Override
    public RentalDTO update(UpdateRentalRequest updateRentalRequest) {
        return entityService.update(updateRentalRequest).toModel();
    }

    @Override
    public RentalDTO getById(int id) {
        return entityService.getById(id).toModel();
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
        carService.removeRental(rentalEntity.getCarEntity().getId(), rentalEntity);
        customerService.removeRental(rentalEntity.getCustomerEntity().getId(), rentalEntity);
        rentalEntity.setIsDeleted(true);
        rentalEntity.setDeletedAt(LocalDateTime.now());
        entityService.update(rentalEntity);
    }

    @Override
    public List<RentalDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<RentalDTO> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public List<RentalDTO> getAllByStatus(int statusId) {
        List<RentalDTO> rentalDTOList = mapToDTOList(entityService.getAllByStatus(statusId));
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
    private List<RentalDTO> mapToDTOList(List<RentalEntity> rentalEntityList) {
        rules.checkDataList(rentalEntityList);
        return rentalEntityList.stream().map(RentalEntity::toModel).toList();
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
