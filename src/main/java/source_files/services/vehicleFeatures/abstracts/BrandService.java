package source_files.services.vehicleFeatures.abstracts;

import source_files.controllers.vehicle.dtos.BrandDTO;
import source_files.controllers.vehicle.requests.vehicleFeatures.brand.CreateBrandRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.brand.UpdateBrandRequest;

import java.util.List;

public interface BrandService {

    void create(CreateBrandRequest createBrandRequest);

    BrandDTO update(UpdateBrandRequest updateBrandRequest);

    BrandDTO getById(int id);

    List<BrandDTO> getAll();

    List<BrandDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);


}
