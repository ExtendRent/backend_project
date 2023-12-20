package source_files.services.entityServices.abstracts;

import source_files.data.models.userEntities.AdminEntity;
import source_files.data.models.vehicleEntities.CarEntity;

import java.util.List;

public interface CarEntityService {

    CarEntity add(CarEntity carEntity);

    CarEntity update(CarEntity carEntity);

    CarEntity getById(int id);

    List<CarEntity> getAll();
    List<CarEntity> getAllByIsDeletedFalse();

    List<CarEntity> getAllByIsDeletedTrue();
    void delete(CarEntity carEntity);
}
