package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;

import java.util.List;

public interface CarModelEntityService {
    CarModelEntity add(CarModelEntity carModelEntity);

    CarModelEntity updateCarModel(CarModelEntity carModelEntity);

    CarModelEntity getCarModelById(int id);

    void deleteCarModel(CarModelEntity carModelEntity);

    List<CarModelEntity> getAllCarModel();
}
