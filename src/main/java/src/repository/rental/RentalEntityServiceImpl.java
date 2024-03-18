package src.repository.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.rental.requests.CreateRentalRequest;
import src.controller.rental.requests.UpdateRentalRequest;
import src.core.exception.DataNotFoundException;
import src.repository.discount.DiscountEntityService;
import src.repository.payment.detail.PaymentDetailsEntityService;
import src.repository.user.customer.CustomerEntityService;
import src.repository.vehicle.car.CarEntityService;
import src.service.rental.status.RentalStatusService;
import src.service.vehicle.car.CarService;

import java.time.LocalDate;
import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.RENTAL_DATA_NOT_FOUND;
import static src.service.rental.status.model.DefaultRentalStatus.WAITING;

@Service
@RequiredArgsConstructor
public class RentalEntityServiceImpl implements RentalEntityService {

    private final RentalRepository repository;
    private final CustomerEntityService customerEntityService;
    private final CarEntityService carEntityService;
    private final DiscountEntityService discountEntityService;
    private final CarService carService;
    private final RentalStatusService rentalStatusService;
    private final PaymentDetailsEntityService paymentDetailsEntityService;

    @Override
    public RentalEntity create(CreateRentalRequest createRentalRequest) {
        RentalEntity rentalEntity = RentalEntity.rentalBuilder()
                .customerEntity(customerEntityService.getById(createRentalRequest.getCustomerEntityId()))
                .carEntity(carEntityService.getById(createRentalRequest.getCarEntityId()))
                .startDate(createRentalRequest.getStartDate())
                .endDate(createRentalRequest.getEndDate())
                .discountEntity(discountEntityService.getByDiscountCode(createRentalRequest.getDiscountCode()))
                .startKilometer(carService.getById(createRentalRequest.getCarEntityId()).getKilometer())
                .rentalStatusEntity(rentalStatusService.getByStatus(WAITING))
                .isActive(true)
                .build();

        return this.repository.save(rentalEntity);
    }

    @Override
    public RentalEntity update(UpdateRentalRequest updateRentalRequest) {
        RentalEntity rentalEntity = RentalEntity.rentalBuilder()
                .id(updateRentalRequest.getId())
                .customerEntity(customerEntityService.getById(updateRentalRequest.getCustomerEntityId()))
                .carEntity(carEntityService.getById(updateRentalRequest.getCarEntityId()))
                .startDate(updateRentalRequest.getStartDate())
                .endDate(updateRentalRequest.getEndDate())
                .discountEntity(discountEntityService.getById(updateRentalRequest.getDiscountEntityId()))
                .paymentDetailsEntity(paymentDetailsEntityService.getById(
                        updateRentalRequest.getPaymentDetailsEntityId()))
                .build();
        return this.repository.save(rentalEntity);
    }

    @Override
    public RentalEntity update(RentalEntity rentalEntity) {
        return this.repository.save(rentalEntity);
    }

    @Override
    public void delete(RentalEntity rentalEntity) {
        this.repository.delete(rentalEntity);
    }

    @Override
    public RentalEntity getById(int id) {
        return this.repository
                .findById(id).orElseThrow(() ->
                        new DataNotFoundException(RENTAL_DATA_NOT_FOUND));
    }

    @Override
    public List<RentalEntity> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<RentalEntity> getAllByDeletedState(boolean isDeleted) {
        return this.repository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public List<RentalEntity> getAllByStatus(int statusId) {
        return repository.findAllByRentalStatusEntity_Id(statusId);
    }

    public List<RentalEntity> getAllOverlappingRentals(LocalDate startDate, LocalDate endDate) {
        return repository.findOverlappingRentals(startDate, endDate);
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return repository.countByIsDeleted(isDeleted);
    }

    @Override
    public int getCountByStatusId(int statusId) {
        return repository.countByRentalStatusEntity_Id(statusId);
    }
}
