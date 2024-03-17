package src.repository.paperwork.discount;

import src.controller.paperwork.discount.requests.CreateDiscountRequest;
import src.controller.paperwork.discount.requests.UpdateDiscountRequest;

import java.util.List;

public interface DiscountEntityService {

    DiscountEntity create(CreateDiscountRequest createDiscountRequest);

    DiscountEntity update(UpdateDiscountRequest updateDiscountRequest);

    DiscountEntity update(DiscountEntity discountEntity);

    void delete(DiscountEntity discountEntity);

    DiscountEntity getById(int id);

    DiscountEntity getByDiscountCode(String discountCode);

    List<DiscountEntity> getAll();

    List<DiscountEntity> getAllByActiveState(boolean isActive);

    List<DiscountEntity> getAllByDeletedState(boolean isDeleted);
}
