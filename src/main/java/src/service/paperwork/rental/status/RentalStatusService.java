package src.service.paperwork.rental.status;

import src.controller.paperwork.rental.responses.RentalStatusResponse;
import src.repository.paperwork.rental.status.RentalStatusEntity;
import src.service.paperwork.rental.status.model.DefaultRentalStatus;

import java.util.List;

public interface RentalStatusService {

    RentalStatusEntity create(RentalStatusEntity rentalStatusEntity);

    RentalStatusEntity update(RentalStatusEntity rentalStatusEntity);

    void delete(RentalStatusEntity rentalStatusEntity);

    RentalStatusEntity getById(int id);

    RentalStatusEntity getByStatus(DefaultRentalStatus status);

    List<RentalStatusResponse> getAll();

}
