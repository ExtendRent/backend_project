package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.BrandEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;

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
