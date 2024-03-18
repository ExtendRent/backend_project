package src.service.discount;

import src.controller.discount.requests.CreateDiscountRequest;
import src.controller.discount.requests.UpdateDiscountRequest;
import src.controller.discount.responses.DiscountResponse;

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
