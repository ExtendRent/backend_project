package source_files.services.entityServices.vehicleFeaturesEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.BrandRespository;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.BrandEntityService;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandEntityManager implements BrandEntityService {

    private final BrandRespository brandRespository;


    @Override
    public BrandEntity add(BrandEntity brandEntity) {
        return brandRespository.save(brandEntity);
    }

    @Override
    public BrandEntity update(BrandEntity brandEntity) {

        return brandRespository.save(brandEntity);
    }

    @Override
    public BrandEntity getById(int id) {

        return brandRespository.findById(id).orElseThrow();
    }

    @Override
    public void delete(BrandEntity brandEntity) {
        brandRespository.delete(brandEntity);
    }

    @Override
    public List<BrandEntity> getAll() {
        return brandRespository.findAll();
    }
}
