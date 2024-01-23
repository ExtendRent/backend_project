package source_files.services.entityServices.abstracts.paperWorkAbstracts;

import source_files.data.models.paperWorkEntities.paymentEntities.DiscountEntity;

import java.util.List;

public interface DiscountEntityService {

    DiscountEntity create(DiscountEntity discountEntity);

    DiscountEntity update(DiscountEntity discountEntity);

    void delete(DiscountEntity discountEntity);

    DiscountEntity getById(int id);

    DiscountEntity getByDiscountCode(String discountCode);

    List<DiscountEntity> getAll();

    List<DiscountEntity> getAllByActiveState(boolean isActive);

    List<DiscountEntity> getAllByDeletedState(boolean isDeleted);

}
