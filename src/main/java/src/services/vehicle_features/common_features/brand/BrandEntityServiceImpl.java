package src.services.vehicle_features.common_features.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.brand.CreateBrandRequest;
import src.controllers.vehicle.requests.vehicleFeatures.brand.UpdateBrandRequest;
import src.core.exception.DataNotFoundException;
import src.data.models.vehicle_entities.vehicle_features.BrandEntity;
import src.repositories.vehicle_features.BrandRepository;
import src.services.image.brand.BrandImageService;

import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.BRAND_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BrandEntityServiceImpl implements BrandEntityService {

    private final BrandRepository repository;
    private final BrandImageService brandImageService;

    @Override
    public BrandEntity create(CreateBrandRequest createBrandRequest) {
        BrandEntity brandEntity = BrandEntity.brandBuilder()
                .name(createBrandRequest.getName())
                .brandImageEntity(brandImageService.getById(createBrandRequest.getBrandImageEntityId()))
                .build();
        return repository.save(brandEntity);
    }

    @Override
    public BrandEntity update(UpdateBrandRequest updateBrandRequest) {
        BrandEntity brandEntity = BrandEntity.brandBuilder()
                .id(updateBrandRequest.getId())
                .name(updateBrandRequest.getName())
                .brandImageEntity(brandImageService.getById(updateBrandRequest.getBrandImageEntityId()))
                .build();
        return repository.save(brandEntity);
    }

    @Override
    public BrandEntity update(BrandEntity brandEntity) {
        return repository.save(brandEntity);
    }

    @Override
    public BrandEntity getById(int id) {

        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundException(BRAND_DATA_NOT_FOUND)
        );
    }

    @Override
    public void delete(BrandEntity brandEntity) {
        repository.delete(brandEntity);
    }

    @Override
    public List<BrandEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<BrandEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }

}
