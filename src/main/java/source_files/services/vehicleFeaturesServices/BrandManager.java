package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.models.imageEntities.BrandImageEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.BrandEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.BrandBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.BrandEntityService;
import source_files.services.systemServices.ImageServices.BrandImageService;
import source_files.services.vehicleFeaturesServices.abstracts.BrandService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandManager implements BrandService {

    private final BrandEntityService entityService;
    private final ModelMapperService modelMapperService;
    private final BrandBusinessRules brandBusinessRules;
    private final BrandImageService brandImageService;

    @Override
    public void create(CreateBrandRequest createBrandRequest) {
        try {
            BrandEntity brandEntity = modelMapperService.forRequest()
                    .map(brandBusinessRules.checkCreateBrandRequest(
                            brandBusinessRules.fixCreateBrandRequest(createBrandRequest)), BrandEntity.class);
            entityService.create(brandEntity);
        } catch (Exception e) {
            brandImageService.delete(createBrandRequest.getBrandImageEntityId());
            throw e;
        }
    }

    @Override
    public BrandDTO update(UpdateBrandRequest updateBrandRequest) {
        BrandEntity brandEntity = entityService.getById(updateBrandRequest.getId());
        BrandImageEntity brandImage = brandEntity.getBrandImageEntity();
        if (brandImage.getId() != updateBrandRequest.getBrandImageEntityId()) {
            brandImageService.delete(brandImage.getId());
        }
        brandEntity = modelMapperService.forRequest()
                .map(brandBusinessRules.checkUpdateBrandRequest(
                        brandBusinessRules.fixUpdateBrandRequest(updateBrandRequest)), BrandEntity.class);
        return modelMapperService.forResponse().map(entityService.update(brandEntity), BrandDTO.class);
    }

    @Override
    public BrandDTO getById(int id) {
        return modelMapperService.forResponse().map(entityService.getById(id), BrandDTO.class);
    }

    @Override
    public List<BrandDTO> getAll() {
        return brandBusinessRules.checkDataList(entityService.getAll())
                .stream().map(brand -> modelMapperService.forResponse()
                        .map(brand, BrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<BrandDTO> getAllByDeletedState(boolean isDeleted) {

        return brandBusinessRules.checkDataList(entityService.getAllByDeletedState(isDeleted))
                .stream().map(brand -> modelMapperService.forResponse()
                        .map(brand, BrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            entityService.delete(entityService.getById(id));
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        BrandEntity brandEntity = entityService.getById(id);
        brandEntity.setIsDeleted(true);
        brandEntity.setDeletedAt(LocalDateTime.now());
        entityService.update(brandEntity);
    }
}
