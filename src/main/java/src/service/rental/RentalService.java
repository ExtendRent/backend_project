package src.service.rental;

import src.controller.rental.request.CreateRentalRequest;
import src.controller.rental.request.ReturnRentalRequest;
import src.controller.rental.request.ShowRentalRequest;
import src.controller.rental.request.UpdateRentalRequest;
import src.controller.rental.response.RentalResponse;
import src.controller.rental.response.ShowRentalResponse;

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
