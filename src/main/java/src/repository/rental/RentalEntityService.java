package src.repository.rental;

import src.controller.rental.request.CreateRentalRequest;
import src.controller.rental.request.UpdateRentalRequest;

import java.time.LocalDate;
import java.util.List;

public interface RentalEntityService {

    RentalEntity create(CreateRentalRequest createRentalRequest);

    RentalEntity update(UpdateRentalRequest updateRentalRequest);

    RentalEntity update(RentalEntity rentalEntity);

    void delete(RentalEntity rentalEntity);

    RentalEntity getById(int id);

    List<RentalEntity> getAll();

    List<RentalEntity> getAllByDeletedState(boolean isDeleted);

    List<RentalEntity> getAllByStatus(int statusId);

    List<RentalEntity> getAllOverlappingRentals(LocalDate startDate, LocalDate endDate);

    int getCountByDeletedState(boolean isDeleted);

    int getCountByStatusId(int statusId);
}
