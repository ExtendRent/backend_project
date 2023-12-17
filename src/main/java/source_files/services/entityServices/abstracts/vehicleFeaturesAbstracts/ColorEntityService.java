package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;

import java.util.List;

public interface ColorEntityService {
    ColorEntity add(ColorEntity colorEntity);

    ColorEntity update(ColorEntity colorEntity);

    ColorEntity getById(int id);

    void delete(ColorEntity colorEntity);

    List<ColorEntity> getAll();

}
