package src.services.vehicle_features.common_features.brand;

import src.controllers.vehicle.requests.vehicleFeatures.brand.CreateBrandRequest;
import src.controllers.vehicle.requests.vehicleFeatures.brand.UpdateBrandRequest;
import src.data.models.vehicle_entities.vehicle_features.BrandEntity;

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
