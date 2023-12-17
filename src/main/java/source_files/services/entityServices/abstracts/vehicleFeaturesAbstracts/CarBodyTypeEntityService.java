package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;

import java.util.List;

public interface CarBodyTypeEntityService {
    CarBodyTypeEntity addCarBodyType(CarBodyTypeEntity carBodyTypeEntity);

    CarBodyTypeEntity updateCarBodyType(CarBodyTypeEntity carBodyTypeEntity);

    CarBodyTypeEntity getCarBodyTypeById(int id);

    void deleteCarBodyType(CarBodyTypeEntity carBodyTypeEntity);

    List<CarBodyTypeEntity> getAllCarBodyType();
}
