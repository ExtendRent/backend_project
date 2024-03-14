package source_files.services.entityServices.abstracts.paperWorkAbstracts;

import source_files.controllers.paperWork.requests.RentalRequests.CreateRentalRequest;
import source_files.controllers.paperWork.requests.RentalRequests.UpdateRentalRequest;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;

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
