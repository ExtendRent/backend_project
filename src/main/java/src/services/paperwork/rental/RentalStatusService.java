package src.services.paperwork.rental;

import src.controllers.paperwork.responses.RentalStatusResponse;
import src.data.enums.default_data_enums.status.DefaultRentalStatus;
import src.data.models.paperwork_entities.rentalEntities.RentalStatusEntity;

import java.util.List;

public interface RentalStatusService {

    RentalStatusEntity create(RentalStatusEntity rentalStatusEntity);

    RentalStatusEntity update(RentalStatusEntity rentalStatusEntity);

    void delete(RentalStatusEntity rentalStatusEntity);

    RentalStatusEntity getById(int id);

    RentalStatusEntity getByStatus(DefaultRentalStatus status);

    List<RentalStatusResponse> getAll();

}
