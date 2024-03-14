package source_files.services.entityServices.paperWorkEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.paperWork.requests.RentalRequests.CreateRentalRequest;
import source_files.controllers.paperWork.requests.RentalRequests.UpdateRentalRequest;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.exception.DataNotFoundException;
import source_files.repositories.paperWork.RentalRepository;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountEntityService;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;
import source_files.services.entityServices.abstracts.userAbstract.CustomerEntityService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.CarEntityService;
import source_files.services.paperWork.abstracts.RentalStatusService;
import source_files.services.system.SysPaymentDetailsService;
import source_files.services.vehicle.abstracts.CarService;

import java.time.LocalDate;
import java.util.List;

import static source_files.data.enums.defaultDataEnums.Status.DefaultRentalStatus.WAITING;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.RENTAL_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RentalEntityServiceImpl implements RentalEntityService {

    private final RentalRepository repository;
    private final CustomerEntityService customerEntityService;
    private final CarEntityService carEntityService;
    private final DiscountEntityService discountEntityService;
    private final CarService carService;
    private final RentalStatusService rentalStatusService;
    private final SysPaymentDetailsService sysPaymentDetailsService;

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
                .paymentDetailsEntity(sysPaymentDetailsService.getById(
                        updateRentalRequest.getPaymentDetailsEntityId()))
                .build();
        return this.repository.save(rentalEntity);
    }

    @Override
    public RentalEntity update(RentalEntity rentalEntity) {
        return null;
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
