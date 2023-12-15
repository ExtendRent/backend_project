package source_files.services.entityServices.abstracts;

import source_files.data.models.vehicleEntities.CarEntity;

public interface CarEntityService {

    CarEntity add(CarEntity carEntity);

    CarEntity update(CarEntity carEntity);

    CarEntity getById(int id);

    void delete(CarEntity carEntity);
}
