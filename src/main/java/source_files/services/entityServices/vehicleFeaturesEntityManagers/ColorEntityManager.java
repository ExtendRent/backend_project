package source_files.services.entityServices.vehicleFeaturesEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.ColorRepository;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.ColorEntityService;

@Service
@AllArgsConstructor
public class ColorEntityManager implements ColorEntityService {

    private final ColorRepository colorRepository;

    @Override
    public ColorEntity addColor(ColorEntity colorEntity) {
        return this.colorRepository.save(colorEntity);
    }

    @Override
    public ColorEntity updateColor(ColorEntity colorEntity) {
        return this.colorRepository.save(colorEntity);
    }

    @Override
    public ColorEntity getColorById(int id) {
        return this.colorRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteColor(ColorEntity colorEntity) {
        this.colorRepository.delete(colorEntity);
    }
}
