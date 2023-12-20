package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.userEntities.AdminEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;

import java.util.List;

public interface CarBodyTypeEntityService {
    CarBodyTypeEntity add(CarBodyTypeEntity carBodyTypeEntity);

    CarBodyTypeEntity update(CarBodyTypeEntity carBodyTypeEntity);

    CarBodyTypeEntity getById(int id);

    void delete(CarBodyTypeEntity carBodyTypeEntity);

    List<CarBodyTypeEntity> getAll();

    List<AdminEntity> getAllByIsDeletedFalse();

    List<AdminEntity> getAllByIsDeletedTrue();
}
