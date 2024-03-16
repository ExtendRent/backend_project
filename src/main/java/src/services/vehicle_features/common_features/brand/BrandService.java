package src.services.vehicle_features.common_features.brand;

import src.controllers.vehicle.requests.vehicleFeatures.brand.CreateBrandRequest;
import src.controllers.vehicle.requests.vehicleFeatures.brand.UpdateBrandRequest;
import src.controllers.vehicle.responses.BrandResponse;

import java.util.List;

public interface BrandService {

    void create(CreateBrandRequest createBrandRequest);

    BrandResponse update(UpdateBrandRequest updateBrandRequest);

    BrandResponse getById(int id);

    List<BrandResponse> getAll();

    List<BrandResponse> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);


}
