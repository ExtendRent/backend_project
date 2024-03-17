package src.repository.vehicle.features.common.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.common.brand.requests.CreateBrandRequest;
import src.controller.vehicle.features.common.brand.requests.UpdateBrandRequest;
import src.core.exception.DataNotFoundException;
import src.service.image.brand.BrandImageService;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.BRAND_DATA_NOT_FOUND;

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
