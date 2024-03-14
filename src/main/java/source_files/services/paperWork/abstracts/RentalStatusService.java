package source_files.services.paperWork.abstracts;

import source_files.controllers.paperWork.dtos.RentalStatusDTO;
import source_files.data.enums.defaultDataEnums.Status.DefaultRentalStatus;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalStatusEntity;

import java.util.List;

public interface RentalStatusService {

    RentalStatusEntity create(RentalStatusEntity rentalStatusEntity);

    RentalStatusEntity update(RentalStatusEntity rentalStatusEntity);

    void delete(RentalStatusEntity rentalStatusEntity);

    RentalStatusEntity getById(int id);

    RentalStatusEntity getByStatus(DefaultRentalStatus status);

    List<RentalStatusDTO> getAll();

}
