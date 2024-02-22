package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.BrandEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.BrandRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.BrandEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.BRAND_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BrandEntityManager implements BrandEntityService {

    private final BrandRepository brandRepository;


    @Override
    public BrandEntity create(BrandEntity brandEntity) {
        return brandRepository.save(brandEntity);
    }

    @Override
    public BrandEntity update(BrandEntity brandEntity) {

        return brandRepository.save(brandEntity);
    }

    @Override
    public BrandEntity getById(int id) {

        return brandRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(BRAND_DATA_NOT_FOUND)
        );
    }

    @Override
    public void delete(BrandEntity brandEntity) {
        brandRepository.delete(brandEntity);
    }

    @Override
    public List<BrandEntity> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public List<BrandEntity> getAllByDeletedState(boolean isDeleted) {
        return brandRepository.findAllByIsDeleted(isDeleted);
    }

}
