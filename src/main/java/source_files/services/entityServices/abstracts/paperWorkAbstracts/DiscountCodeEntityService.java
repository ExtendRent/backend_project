package source_files.services.entityServices.abstracts.paperWorkAbstracts;

import source_files.data.models.paperWorkEntities.paymentEntities.DiscountCodeEntity;

import java.util.List;

public interface DiscountCodeEntityService {

    DiscountCodeEntity add(DiscountCodeEntity discountCodeEntity);

    DiscountCodeEntity update(DiscountCodeEntity discountCodeEntity);

    void delete(DiscountCodeEntity discountCodeEntity);

    DiscountCodeEntity getById(int id);

    DiscountCodeEntity getByDiscountCode(String discountCode);

    List<DiscountCodeEntity> getAll();

    List<DiscountCodeEntity> getAllByIsActiveTrue();

    List<DiscountCodeEntity> getAllByIsActiveFalse();

    List<DiscountCodeEntity> getAllByIsDeletedFalse();

    List<DiscountCodeEntity> getAllByIsDeletedTrue();

}
