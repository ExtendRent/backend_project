package source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;

import java.util.List;

public interface ColorEntityService {
    ColorEntity add(ColorEntity colorEntity);

    ColorEntity update(ColorEntity colorEntity);

    ColorEntity getById(int id);

    List<ColorEntity> getAll();

    List<ColorEntity> getAllByIsDeletedFalse();

    List<ColorEntity> getAllByIsDeletedTrue();

    void delete(ColorEntity colorEntity);

    void hardDelete(int id);

    void softDelete(int id);


}
