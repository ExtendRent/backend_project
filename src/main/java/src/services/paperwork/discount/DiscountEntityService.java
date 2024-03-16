package src.services.paperwork.discount;

import src.controllers.paperwork.requests.discount.CreateDiscountRequest;
import src.controllers.paperwork.requests.discount.UpdateDiscountRequest;
import src.data.models.paperwork_entities.paymentEntities.DiscountEntity;

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
