package source_files.services.paperWorkServices.abstracts;

import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.DTO.paperWorkDTOs.ShowRentalResponse;
import source_files.data.requests.paperworkRequests.RentalRequests.CreateRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ShowRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.UpdateRentalRequest;

import java.time.LocalDate;
import java.util.List;

public interface RentalService {

    ShowRentalResponse showRentalDetails(ShowRentalRequest showRentalRequest);

    void create(CreateRentalRequest createRentalRequest);

    RentalDTO returnCar(ReturnRentalRequest returnRentalRequest);

    RentalDTO update(UpdateRentalRequest updateRentalRequest);

    RentalDTO getById(int id);

    void delete(int id, boolean isHardDelete);

    void softDelete(int id);

    List<RentalDTO> getAll();

    List<RentalDTO> getAllByDeletedState(boolean isDeleted);

    List<RentalDTO> getAllOverlappingRentals(LocalDate startDate, LocalDate endDate);
}
