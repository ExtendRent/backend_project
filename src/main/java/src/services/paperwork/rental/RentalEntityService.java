package src.services.paperwork.rental;

import src.controllers.paperwork.requests.Rental.CreateRentalRequest;
import src.controllers.paperwork.requests.Rental.UpdateRentalRequest;
import src.data.models.paperwork_entities.rentalEntities.RentalEntity;

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
