package source_files.services.entityServices.abstracts.paperWorkAbstracts;

import source_files.data.models.paperWorkEntities.paymentEntities.DiscountEntity;
import source_files.data.requests.paperworkRequests.discountRequests.CreateDiscountRequest;
import source_files.data.requests.paperworkRequests.discountRequests.UpdateDiscountRequest;

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
