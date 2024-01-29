package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.ColorRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ColorEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.COLOR_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ColorEntityManager implements ColorEntityService {

    private final ColorRepository colorRepository;

    @Override
    public ColorEntity create(ColorEntity colorEntity) {
        return colorRepository.save(colorEntity);
    }

    @Override
    public ColorEntity update(ColorEntity colorEntity) {
        return this.create(colorEntity);
    }


    @Override
    public ColorEntity getById(int id) {
        return this.colorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(COLOR_DATA_NOT_FOUND, "Renk bulunamadı"));
    }

    @Override
    public void delete(ColorEntity colorEntity) {
        colorRepository.delete(colorEntity);
    }


    @Override
    public List<ColorEntity> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public List<ColorEntity> getAllByDeletedState(boolean isDeleted) {
        return colorRepository.findAllByIsDeleted(isDeleted);
    }
}