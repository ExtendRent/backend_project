package source_files.services.paperWorkServices.abstracts;

import org.apache.coyote.BadRequestException;
import source_files.data.DTO.paperWorkDTOs.PaymentDetailsDTO;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.DTO.paperWorkDTOs.ShowRentalResponse;
import source_files.data.requests.paperworkRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.UpdateRentalRequest;

import java.util.List;

public interface RentalService {

    ShowRentalResponse showRentalDetails(AddRentalRequest addRentalRequest);

    RentalDTO add(AddRentalRequest addRentalRequest, PaymentDetailsDTO paymentDetailsDTO) throws BadRequestException;

    RentalDTO returnCar(ReturnRentalRequest returnRentalRequest);

    RentalDTO update(UpdateRentalRequest updateRentalRequest);

    RentalDTO getById(int id);

    void delete(int id);

    void softDelete(int id);

    List<RentalDTO> getAll();

    List<RentalDTO> getAllByIsDeletedFalse();

    List<RentalDTO> getAllByIsDeletedTrue();
}
