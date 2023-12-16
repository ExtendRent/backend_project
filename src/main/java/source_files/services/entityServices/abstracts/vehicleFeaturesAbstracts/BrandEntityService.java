package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;

public interface BrandEntityService{

    BrandEntity addBrand(BrandEntity brandEntity);

    BrandEntity updateBrand(BrandEntity brandEntity);

    BrandEntity getBrandById(int id);

    void deleteBrand(BrandEntity brandEntity);



}
