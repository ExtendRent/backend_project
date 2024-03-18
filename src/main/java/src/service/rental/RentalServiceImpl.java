package src.service.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.controller.rental.requests.CreateRentalRequest;
import src.controller.rental.requests.ReturnRentalRequest;
import src.controller.rental.requests.ShowRentalRequest;
import src.controller.rental.requests.UpdateRentalRequest;
import src.controller.rental.responses.RentalResponse;
import src.controller.rental.responses.ShowRentalResponse;
import src.core.exception.ValidationException;
import src.repository.payment.detail.PaymentDetailsEntityService;
import src.repository.rental.RentalEntity;
import src.repository.rental.RentalEntityService;
import src.repository.vehicle.car.CarEntity;
import src.service.discount.model.DefaultDiscount;
import src.service.payment.PaymentService;
import src.service.rental.status.RentalStatusService;
import src.service.rental.status.model.DefaultRentalStatus;
import src.service.user.customer.CustomerService;
import src.service.vehicle.car.CarService;

import java.time.LocalDateTime;
import java.util.List;

import static src.core.exception.type.ValidationExceptionType.VALIDATION_EXCEPTION;
import static src.service.vehicle.features.common.status.model.DefaultVehicleStatus.IN_USE;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalEntityService entityService;
    private final CarService carService;
    private final PaymentDetailsEntityService paymentDetailsEntityService;
    private final CustomerService customerService;
    private final RentalRules rules;
    private final PaymentService paymentService;
    private final RentalStatusService rentalStatusService;

    @Override
    public ShowRentalResponse showRentalDetails(ShowRentalRequest showRentalRequest) {
        rules.check(showRentalRequest);
        if (carService.isCarAvailableBetween(showRentalRequest.getCarEntityId(),
                showRentalRequest.getStartDate(), showRentalRequest.getEndDate())
        ) {
            return convertToShowRentalResponse(showRentalRequest);
        }
        throw new ValidationException(VALIDATION_EXCEPTION, "Araç bu tarihler arasında kiralanamaz");
    }

    @Transactional
    @Override
    public void create(CreateRentalRequest createRentalRequest) {
        createRentalRequest = rules.fix(createRentalRequest);
        rules.check(createRentalRequest);
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
    public RentalResponse returnCar(ReturnRentalRequest returnRentalRequest) {
        // ceza işlemleri , indirim işlemleri iptali kontrol edilecek sonuçta da totalPrice güncelleme
        rules.checkIsActive(returnRentalRequest.getId());
        RentalEntity rentalEntity = this.returnReqeustToEntity(returnRentalRequest);
        CarEntity carEntity = rentalEntity.getCarEntity();
        carEntity.setKilometer(rentalEntity.getEndKilometer());
        if (rentalEntity.getCarEntity() != null) {
            carService.removeRental(carEntity.getId(), rentalEntity);
        }
        return entityService.update(rentalEntity).toModel();
    }

    @Override
    public RentalResponse startRental(int rentalId) {
        rules.checkIsActive(rentalId);
        RentalEntity rentalEntity = entityService.getById(rentalId);
        CarEntity carEntity = rentalEntity.getCarEntity();
        rentalEntity.setStartKilometer(carEntity.getKilometer());
        rentalEntity.setRentalStatusEntity(rentalStatusService.getByStatus(DefaultRentalStatus.ACTIVE));
        carService.changeStatus(carEntity, IN_USE);
        return entityService.update(rentalEntity).toModel();
    }

    @Transactional
    @Override
    public RentalResponse cancelRental(int rentalId) {
        rules.checkIsActive(rentalId);
        softDelete(rentalId);
        RentalEntity rentalEntity = entityService.getById(rentalId);
        CarEntity carEntity = rentalEntity.getCarEntity();
        if (rentalEntity.getCarEntity() != null) {
            carService.removeRental(carEntity.getId(), rentalEntity);
        }
        customerService.removeRental(rentalEntity.getCustomerEntity().getId(), rentalEntity);
        rentalEntity.setRentalStatusEntity(rentalStatusService.getByStatus(DefaultRentalStatus.CANCELED));
        return entityService.update(rentalEntity).toModel();
    }

    @Override
    public RentalResponse update(UpdateRentalRequest updateRentalRequest) {
        return entityService.update(updateRentalRequest).toModel();
    }

    @Override
    public RentalResponse getById(int id) {
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
        rentalEntity.setActive(false);
        rentalEntity.setDeletedAt(LocalDateTime.now());
        entityService.update(rentalEntity);
    }

    @Override
    public List<RentalResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Transactional
    @Override
    public List<RentalResponse> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public List<RentalResponse> getAllByStatus(int statusId) {
        List<RentalResponse> rentalResponseList = mapToDTOList(entityService.getAllByStatus(statusId));
        List<RentalResponse> collectList = rentalResponseList.stream().filter(RentalResponse::isActive).toList();
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
    private List<RentalResponse> mapToDTOList(List<RentalEntity> rentalEntityList) {
        rules.checkDataList(rentalEntityList);
        return rentalEntityList.stream().map(RentalEntity::toModel).toList();
    }

    private ShowRentalResponse convertToShowRentalResponse(ShowRentalRequest showRentalRequest) {
        // indirim işlemleri sonucu totalPrice hesaplama
        return ShowRentalResponse.builder()
                .amount(rules.calculateAmount(showRentalRequest))
                .customerResponse(customerService.getById(showRentalRequest.getCustomerEntityId()))
                .carResponse(carService.getById(showRentalRequest.getCarEntityId()))
                .discountCode(showRentalRequest.getDiscountCode())
                .startDate(showRentalRequest.getStartDate())
                .endDate(showRentalRequest.getEndDate())
                .build();
    }

    private RentalEntity returnReqeustToEntity(ReturnRentalRequest returnRentalRequest) {
        RentalEntity rentalEntity = entityService.getById(returnRentalRequest.getId());
        rentalEntity.setPaymentDetailsEntity(paymentDetailsEntityService.update(
                rules.updatePaymentDetailsToFinal(returnRentalRequest)));

        rentalEntity.setEndKilometer(returnRentalRequest.getEndKilometer());
        rentalEntity.setActive(false);
        rentalEntity.setRentalStatusEntity(rentalStatusService.getByStatus(DefaultRentalStatus.FINISHED));
        rentalEntity.setReturnDate(returnRentalRequest.getReturnDate());
        return rentalEntity;
    }

}
