package src.repository.vehicle.features.common.brand;

import src.controller.vehicle.features.common.brand.requests.CreateBrandRequest;
import src.controller.vehicle.features.common.brand.requests.UpdateBrandRequest;

import java.util.List;

public interface BrandEntityService {

    BrandEntity create(CreateBrandRequest createBrandRequest);

    BrandEntity update(UpdateBrandRequest updateBrandRequest);

    BrandEntity update(BrandEntity brandEntity);

    BrandEntity getById(int id);

    List<BrandEntity> getAll();

    List<BrandEntity> getAllByDeletedState(boolean isDeleted);

    void delete(BrandEntity brandEntity);

}
