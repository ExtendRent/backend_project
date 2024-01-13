package source_files.services.paperWorkServices.abstracts;

import org.apache.coyote.BadRequestException;
import source_files.data.DTO.paperWorkDTOs.DiscountCodeDTO;
import source_files.data.requests.paperworkRequests.discountRequests.AddDiscountCodeRequest;
import source_files.data.requests.paperworkRequests.discountRequests.UpdateDiscountCodeRequest;

import java.util.List;

public interface DiscountCodeService {
    DiscountCodeDTO add(AddDiscountCodeRequest addDiscountCodeRequest) throws BadRequestException;

    DiscountCodeDTO update(UpdateDiscountCodeRequest updateDiscountCodeRequest);

    DiscountCodeDTO getById(int id);

    DiscountCodeDTO getByDiscountCode(String discountCode);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    List<DiscountCodeDTO> getAll();

    List<DiscountCodeDTO> getAllByIsActiveTrue();

    List<DiscountCodeDTO> getAllByIsActiveFalse();

    List<DiscountCodeDTO> getAllByIsDeletedFalse();

    List<DiscountCodeDTO> getAllByIsDeletedTrue();

}
