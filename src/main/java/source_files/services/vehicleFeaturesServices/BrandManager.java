package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.AddBrandRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.services.entityServices.vehicleFeaturesEntityManagers.BrandEntityManager;
import source_files.services.vehicleFeaturesServices.abstracts.BrandService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private BrandEntityManager brandEntityManager;
    private ModelMapperService modelMapperService;

    @Override
    public BrandDTO add(AddBrandRequest addBrandRequest) {
        BrandEntity brandEntity = modelMapperService.forRequest().map(addBrandRequest,BrandEntity.class);
        BrandDTO brandDTO = modelMapperService.forResponse().map(brandEntityManager.add(brandEntity), BrandDTO.class);
        return brandDTO;
    }

    @Override
    public BrandDTO update(UpdateBrandRequest updateBrandRequest) {
        BrandEntity brandEntity = modelMapperService.forRequest().map(updateBrandRequest, BrandEntity.class);
        BrandDTO brandDTO = modelMapperService.forResponse().map(brandEntityManager.update(brandEntity), BrandDTO.class);
        return brandDTO;
    }

    @Override
    public BrandDTO getById(int id) {
        BrandDTO brandDTO = modelMapperService.forResponse().map(brandEntityManager.getById(id), BrandDTO.class);
        return brandDTO;
    }

    @Override
    public void delete(int id) {
        brandEntityManager.delete(brandEntityManager.getById(id));
    }

    @Override
    public List<BrandDTO> getAll() {
        List<BrandEntity> brandList = brandEntityManager.getAll();
        List<BrandDTO> brandDTOSList = brandList.stream().map(brand -> modelMapperService.forResponse().map(brand, BrandDTO.class)).collect(Collectors.toList());
        return brandDTOSList;
    }
}
