package source_files.services.paperWork.abstracts;

import source_files.controllers.paperWork.dtos.RentalDTO;
import source_files.controllers.paperWork.dtos.ShowRentalResponse;
import source_files.controllers.paperWork.requests.RentalRequests.CreateRentalRequest;
import source_files.controllers.paperWork.requests.RentalRequests.ReturnRentalRequest;
import source_files.controllers.paperWork.requests.RentalRequests.ShowRentalRequest;
import source_files.controllers.paperWork.requests.RentalRequests.UpdateRentalRequest;

import java.util.List;

public interface RentalService {

    ShowRentalResponse showRentalDetails(ShowRentalRequest showRentalRequest);

    void create(CreateRentalRequest createRentalRequest);

    RentalDTO returnCar(ReturnRentalRequest returnRentalRequest);

    RentalDTO startRental(int rentalId);


    RentalDTO update(UpdateRentalRequest updateRentalRequest);

    RentalDTO getById(int id);

    void delete(int id, boolean isHardDelete);

    void softDelete(int id);

    List<RentalDTO> getAll();

    List<RentalDTO> getAllByDeletedState(boolean isDeleted);

    List<RentalDTO> getAllByStatus(int isDeleted);

    int getCountByDeletedState(boolean isDeleted);

    int getCountByStatusId(int statusId);
}
