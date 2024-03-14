package source_files.services.paperWork.abstracts;

import source_files.controllers.paperWork.dtos.DiscountDTO;
import source_files.controllers.paperWork.requests.discountRequests.CreateDiscountRequest;
import source_files.controllers.paperWork.requests.discountRequests.UpdateDiscountRequest;

import java.util.List;

public interface DiscountService {
    void create(CreateDiscountRequest createDiscountRequest);

    DiscountDTO update(UpdateDiscountRequest updateDiscountRequest);

    DiscountDTO getById(int id);

    DiscountDTO getByDiscountCode(String discountCode);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    List<DiscountDTO> getAll();

    List<DiscountDTO> getAllByDeletedState(boolean isDeleted);

    List<DiscountDTO> getAllByActiveState(boolean isActive);

}
