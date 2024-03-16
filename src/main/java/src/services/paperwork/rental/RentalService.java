package src.services.paperwork.rental;

import src.controllers.paperwork.requests.Rental.CreateRentalRequest;
import src.controllers.paperwork.requests.Rental.ReturnRentalRequest;
import src.controllers.paperwork.requests.Rental.ShowRentalRequest;
import src.controllers.paperwork.requests.Rental.UpdateRentalRequest;
import src.controllers.paperwork.responses.RentalResponse;
import src.controllers.paperwork.responses.ShowRentalResponse;

import java.util.List;

public interface RentalService {

    ShowRentalResponse showRentalDetails(ShowRentalRequest showRentalRequest);

    void create(CreateRentalRequest createRentalRequest);

    RentalResponse returnCar(ReturnRentalRequest returnRentalRequest);

    RentalResponse startRental(int rentalId);


    RentalResponse update(UpdateRentalRequest updateRentalRequest);

    RentalResponse getById(int id);

    void delete(int id, boolean isHardDelete);

    void softDelete(int id);

    List<RentalResponse> getAll();

    List<RentalResponse> getAllByDeletedState(boolean isDeleted);

    List<RentalResponse> getAllByStatus(int isDeleted);

    int getCountByDeletedState(boolean isDeleted);

    int getCountByStatusId(int statusId);
}
