package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;

import java.util.List;

public interface BrandService {

    void create(CreateBrandRequest createBrandRequest);

    BrandDTO update(UpdateBrandRequest updateBrandRequest);

    BrandDTO getById(int id);

    BrandDTO getByName(String brandName);

    List<BrandDTO> getAll();

    List<BrandDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);


}
