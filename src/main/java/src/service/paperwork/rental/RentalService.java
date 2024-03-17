package src.service.paperwork.rental;

import src.controller.paperwork.rental.requests.CreateRentalRequest;
import src.controller.paperwork.rental.requests.ReturnRentalRequest;
import src.controller.paperwork.rental.requests.ShowRentalRequest;
import src.controller.paperwork.rental.requests.UpdateRentalRequest;
import src.controller.paperwork.rental.responses.RentalResponse;
import src.controller.paperwork.rental.responses.ShowRentalResponse;

import java.util.List;

public interface RentalService {

    ShowRentalResponse showRentalDetails(ShowRentalRequest showRentalRequest);

    void create(CreateRentalRequest createRentalRequest);

    RentalResponse returnCar(ReturnRentalRequest returnRentalRequest);

    RentalResponse startRental(int rentalId);

    RentalResponse cancelRental(int rentalId);

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
