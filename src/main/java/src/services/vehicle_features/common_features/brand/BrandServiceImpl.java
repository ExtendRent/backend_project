package src.services.vehicle_features.common_features.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.brand.CreateBrandRequest;
import src.controllers.vehicle.requests.vehicleFeatures.brand.UpdateBrandRequest;
import src.controllers.vehicle.responses.BrandResponse;
import src.data.models.image_entities.BrandImageEntity;
import src.data.models.vehicle_entities.vehicle_features.BrandEntity;
import src.services.image.brand.BrandImageService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandEntityService entityService;
    private final BrandRules rules;
    private final BrandImageService brandImageService;

    @Override
    public void create(CreateBrandRequest createBrandRequest) {
        try {
            createBrandRequest = rules.fix(createBrandRequest);
            rules.check(createBrandRequest);
            entityService.create(createBrandRequest);
        } catch (Exception e) {
            brandImageService.delete(createBrandRequest.getBrandImageEntityId());
            throw e;
        }
    }

    @Override
    public BrandResponse update(UpdateBrandRequest updateBrandRequest) {
        updateBrandRequest = rules.fix(updateBrandRequest);
        rules.check(updateBrandRequest);
        BrandImageEntity brandImage = entityService.getById(updateBrandRequest.getId()).getBrandImageEntity();

        if (brandImage.getId() != updateBrandRequest.getBrandImageEntityId()) {
            brandImageService.delete(brandImage.getId());
        }
        return entityService.update(updateBrandRequest).toModel();
    }

    @Override
    public BrandResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<BrandResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<BrandResponse> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            entityService.delete(entityService.getById(id));
            brandImageService.delete(entityService.getById(id).getBrandImageEntity().getId());
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

    private List<BrandResponse> mapToDTOList(List<BrandEntity> brandEntities) {
        rules.checkDataList(brandEntities);
        return brandEntities.stream().map(BrandEntity::toModel).toList();
    }
}
