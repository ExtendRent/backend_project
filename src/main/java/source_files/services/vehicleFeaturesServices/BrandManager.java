package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.BrandBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.BrandEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.BrandService;

import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.types.itemTypes.ItemType.BRAND;

@Service
@RequiredArgsConstructor
public class BrandManager implements BrandService {

    private final BrandEntityService brandEntityService;
    private final ModelMapperService modelMapperService;
    private final BrandBusinessRules brandBusinessRules;

    @Override
    public void create(CreateBrandRequest createBrandRequest) {
        BrandEntity brandEntity = modelMapperService.forRequest()
                .map(brandBusinessRules.checkCreateBrandRequest(
                        brandBusinessRules.fixCreateBrandRequest(createBrandRequest)), BrandEntity.class);
        brandEntity.setItemType(BRAND);
        brandEntityService.create(brandEntity);
    }

    @Override
    public BrandDTO update(UpdateBrandRequest updateBrandRequest) {
        BrandEntity brandEntity = modelMapperService.forRequest()
                .map(brandBusinessRules.checkUpdateBrandRequest(
                        brandBusinessRules.fixUpdateBrandRequest(updateBrandRequest)), BrandEntity.class);
        brandEntity.setItemType(BRAND);
        return modelMapperService.forResponse().map(brandEntityService.update(brandEntity), BrandDTO.class);
    }

    @Override
    public BrandDTO getById(int id) {
        return modelMapperService.forResponse().map(brandEntityService.getById(id), BrandDTO.class);
    }

    @Override
    public BrandDTO getByName(String brandName) {
        return modelMapperService.forResponse().map(brandEntityService.getByName(brandName), BrandDTO.class);
    }

    @Override
    public List<BrandDTO> getAll() {
        return brandBusinessRules.checkDataList(brandEntityService.getAll())
                .stream().map(brand -> modelMapperService.forResponse()
                        .map(brand, BrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<BrandDTO> getAllByDeletedState(boolean isDeleted) {

        return brandBusinessRules.checkDataList(brandEntityService.getAllByDeletedState(isDeleted))
                .stream().map(brand -> modelMapperService.forResponse()
                        .map(brand, BrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            this.brandEntityService.delete(this.brandEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        BrandEntity brandEntity = this.brandEntityService.getById(id);
        brandEntity.setIsDeleted(true);
        this.brandEntityService.update(brandEntity);
    }
}
