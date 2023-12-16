package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;

import java.util.List;

public interface BrandEntityService{

    BrandEntity add(BrandEntity brandEntity);

    BrandEntity update(BrandEntity brandEntity);

    BrandEntity getById(int id);

    void delete(BrandEntity brandEntity);
    List<BrandEntity> getAll();



}
