package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;

import java.util.List;

public interface CarModelEntityService {
    CarModelEntity add(CarModelEntity carModelEntity);

    CarModelEntity update(CarModelEntity carModelEntity);

    CarModelEntity getById(int id);

    CarModelEntity getByModelName(String modelName);

    List<CarModelEntity> getAll();

    List<CarModelEntity> getAllByIsDeletedFalse();

    List<CarModelEntity> getAllByIsDeletedTrue();

    List<CarModelEntity> getAllByBrandId(int id);

    void delete(CarModelEntity carModelEntity);
}




