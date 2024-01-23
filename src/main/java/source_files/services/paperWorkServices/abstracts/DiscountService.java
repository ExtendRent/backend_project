package source_files.services.paperWorkServices.abstracts;

import source_files.data.DTO.paperWorkDTOs.DiscountDTO;
import source_files.data.requests.paperworkRequests.discountRequests.CreateDiscount;
import source_files.data.requests.paperworkRequests.discountRequests.UpdateDiscountRequest;

import java.util.List;

public interface DiscountService {
    void create(CreateDiscount createDiscount);

    DiscountDTO update(UpdateDiscountRequest updateDiscountRequest);

    DiscountDTO getById(int id);

    DiscountDTO getByDiscountCode(String discountCode);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    List<DiscountDTO> getAll();

    List<DiscountDTO> getAllByDeletedState(boolean isDeleted);


}
