package source_files.services.entityServices.vehicleFeaturesEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.BrandRespository;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.BrandEntityService;

@Service
@AllArgsConstructor
public class BrandEntityManager implements BrandEntityService {

    private final BrandRespository brandRespository;

    @Override
    public BrandEntity addBrand(BrandEntity brandEntity) {
        return this.brandRespository.save(brandEntity);
    }

    @Override
    public BrandEntity updateBrand(BrandEntity brandEntity) {
        return this.brandRespository.save(brandEntity);
    }

    @Override
    public BrandEntity getBrandById(int id) {
        return this.brandRespository.findById(id).orElseThrow();
    }

    @Override
    public void deleteBrand(BrandEntity brandEntity) {
        this.brandRespository.delete(brandEntity);
    }
}
