package source_files.services.entityServices.abstracts;

import source_files.data.models.vehicleEntities.CarEntity;

import java.util.List;

public interface CarEntityService {

    void add(CarEntity carEntity);

    CarEntity update(CarEntity carEntity);

    CarEntity getById(int id);

    List<CarEntity> getAll();

    List<CarEntity> getAllByDeletedState(boolean isDeleted);

    List<CarEntity> getAllByAvailability(boolean isAvailable);

    List<CarEntity> getAllByColorId(int id);

    List<CarEntity> getAllByModelId(int id);

    List<CarEntity> getAllByYearBetween(int year1, int year2);

    List<CarEntity> getAllByBrandId(int id);

    void delete(CarEntity carEntity);
}
