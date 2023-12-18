package source_files.services.paperWorkServices.abstracts;

import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.UpdateRentalRequest;

import java.util.List;

public interface RentalService {

    RentalDTO add(AddRentalRequest addRentalRequest);

    RentalDTO update(UpdateRentalRequest updateRentalRequest);

    RentalDTO getById(int id);

    void delete(int id);

    List<RentalDTO> getAll();

}
