package source_files.services.paperWorkServices.abstracts;

import org.apache.coyote.BadRequestException;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.UpdateRentalRequest;

import java.util.List;

public interface RentalService {

    RentalDTO add(AddRentalRequest addRentalRequest) throws BadRequestException;

    RentalDTO returnCar(ReturnRentalRequest returnRentalRequest);

    RentalDTO update(UpdateRentalRequest updateRentalRequest);

    RentalDTO getById(int id);

    void delete(int id);

    void softDelete(int id);

    List<RentalDTO> getAll();

    List<RentalDTO> getAllByIsDeletedFalse();

    List<RentalDTO> getAllByIsDeletedTrue();
}
