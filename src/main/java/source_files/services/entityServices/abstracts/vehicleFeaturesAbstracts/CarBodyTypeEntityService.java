package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;

import java.util.List;

public interface CarBodyTypeEntityService {
    CarBodyTypeEntity add(CarBodyTypeEntity carBodyTypeEntity);

    CarBodyTypeEntity update(CarBodyTypeEntity carBodyTypeEntity);

    CarBodyTypeEntity getById(int id);

    void delete(CarBodyTypeEntity carBodyTypeEntity);

    List<CarBodyTypeEntity> getAll();

    List<CarBodyTypeEntity> getAllByIsDeletedFalse();

    List<CarBodyTypeEntity> getAllByIsDeletedTrue();

    void hardDelete(int id);

    void softDelete(int id);
}
