package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;

public interface CarModelEntityService {
    CarModelEntity addCarModel(CarModelEntity carModelEntity);

    CarModelEntity updateCarModel(CarModelEntity carModelEntity);

    CarModelEntity getCarModelById(int id);

    void deleteCarModel(CarModelEntity carModelEntity);
}
