package source_files.services.entityServices.abstracts;

import source_files.data.models.vehicleEntities.CarEntity;

import java.util.List;

public interface CarEntityService {

    CarEntity add(CarEntity carEntity);

    CarEntity update(CarEntity carEntity);

    CarEntity getById(int id);

    List<CarEntity> getAll();

    List<CarEntity> getAllByIsDeletedFalse();

    List<CarEntity> getAllByIsDeletedTrue();

    List<CarEntity> getAllByIsAvailableTrue();

    List<CarEntity> getAllByIsAvailableFalse();

    List<CarEntity> getAllByColorId(int id);

    List<CarEntity> getAllByModelId(int id);

    List<CarEntity> getAllByYearBetween(int year1, int year2);

    List<CarEntity> getAllByBrandId(int id);

    void delete(CarEntity carEntity);
}
