package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.AddBrandRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.BrandEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.BrandService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private BrandEntityService brandEntityService;
    private ModelMapperService modelMapperService;

    @Override
    public BrandDTO add(AddBrandRequest addBrandRequest) {
        BrandEntity brandEntity = modelMapperService.forRequest().map(addBrandRequest, BrandEntity.class);
        BrandDTO brandDTO = modelMapperService.forResponse().map(brandEntityService.add(brandEntity), BrandDTO.class);
        return brandDTO;
    }

    @Override
    public BrandDTO update(UpdateBrandRequest updateBrandRequest) {
        BrandEntity brandEntity = modelMapperService.forRequest().map(updateBrandRequest, BrandEntity.class);
        BrandDTO brandDTO = modelMapperService.forResponse().map(brandEntityService.update(brandEntity), BrandDTO.class);
        return brandDTO;
    }

    @Override
    public BrandDTO getById(int id) {
        BrandDTO brandDTO = modelMapperService.forResponse().map(brandEntityService.getById(id), BrandDTO.class);
        return brandDTO;
    }

    @Override
    public void delete(int id) {
        brandEntityService.delete(brandEntityService.getById(id));
    }

    @Override
    public List<BrandDTO> getAll() {
        List<BrandEntity> brandList = brandEntityService.getAll();
        List<BrandDTO> brandDTOSList = brandList.stream().map(brand -> modelMapperService.forResponse().map(brand, BrandDTO.class)).collect(Collectors.toList());
        return brandDTOSList;
    }
}
