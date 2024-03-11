package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.BrandEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.BrandRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.BrandEntityService;
import source_files.services.systemServices.ImageServices.BrandImageService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.BRAND_DATA_NOT_FOUND;

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
