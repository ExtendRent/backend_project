package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.models.baseEntities.BaseEntity;
import source_files.data.models.userEntities.EmployeeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.AddBrandRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.services.BusinessRules.BrandBusinessRules;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.BrandEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.BrandService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private final BrandEntityService brandEntityService;
    private final ModelMapperService modelMapperService;
    private final BrandBusinessRules brandBusinessRules;

    @Override
    public BrandDTO add(AddBrandRequest addBrandRequest) {
        brandBusinessRules.existsByName(addBrandRequest.getName());
        BrandEntity brandEntity = modelMapperService.forRequest().map(addBrandRequest, BrandEntity.class);
        return modelMapperService.forResponse().map(brandEntityService.add(brandEntity), BrandDTO.class);
    }

    @Override
    public BrandDTO update(UpdateBrandRequest updateBrandRequest) {
        BrandEntity brandEntity = modelMapperService.forRequest().map(updateBrandRequest, BrandEntity.class);
        return modelMapperService.forResponse().map(brandEntityService.update(brandEntity), BrandDTO.class);
    }

    @Override
    public BrandDTO getById(int id) {
        return modelMapperService.forResponse().map(brandEntityService.getById(id), BrandDTO.class);
    }

    @Override
    public List<BrandDTO> getAll() {
        List<BrandEntity> brandList = brandEntityService.getAll();
        return brandList.stream().map(brand -> modelMapperService.forResponse().map(brand, BrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<BrandDTO> getAllByIsDeletedFalse() {
        return this.brandEntityService.getAllByIsDeletedFalse()
                .stream().map(brandEntity ->  modelMapperService.forResponse().map(brandEntity, BrandDTO.class)).toList();
    }

    @Override
    public List<BrandDTO> getAllByIsDeletedTrue() {
        return this.brandEntityService.getAllByIsDeletedTrue()
                .stream().map(brandEntity ->  modelMapperService.forResponse().map(brandEntity, BrandDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if(hardDelete){
            this.hardDelete(id);
        }else{
            this.softDelete(id);
        }
    }

    @Override
    public void hardDelete(int id) {
        this.brandEntityService.delete(this.brandEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {
        BrandEntity brandEntity = this.brandEntityService.getById(id);
        brandEntity.setIsDeleted(true);
        this.brandEntityService.update(brandEntity);
    }
}
