package src.service.vehicle.features.common.brand;

import src.controller.vehicle.features.common.brand.requests.CreateBrandRequest;
import src.controller.vehicle.features.common.brand.requests.UpdateBrandRequest;
import src.controller.vehicle.features.common.brand.responses.BrandResponse;

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
