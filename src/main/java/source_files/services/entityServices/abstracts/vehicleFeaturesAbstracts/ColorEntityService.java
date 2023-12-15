package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;

public interface ColorEntityService {
    ColorEntity addColor(ColorEntity colorEntity);

    ColorEntity updateColor(ColorEntity colorEntity);

    ColorEntity getColorById(int id);

    void deleteColor(ColorEntity colorEntity);
}
