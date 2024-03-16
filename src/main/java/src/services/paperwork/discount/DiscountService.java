package src.services.paperwork.discount;

import src.controllers.paperwork.requests.discount.CreateDiscountRequest;
import src.controllers.paperwork.requests.discount.UpdateDiscountRequest;
import src.controllers.paperwork.responses.DiscountResponse;

import java.util.List;

public interface DiscountService {
    void create(CreateDiscountRequest createDiscountRequest);

    DiscountResponse update(UpdateDiscountRequest updateDiscountRequest);

    DiscountResponse getById(int id);

    DiscountResponse getByDiscountCode(String discountCode);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    List<DiscountResponse> getAll();

    List<DiscountResponse> getAllByDeletedState(boolean isDeleted);

    List<DiscountResponse> getAllByActiveState(boolean isActive);

}
