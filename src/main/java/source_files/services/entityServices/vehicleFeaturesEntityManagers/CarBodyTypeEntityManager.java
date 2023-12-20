package source_files.services.entityServices.vehicleFeaturesEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.AdminEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.CarBodyTypeRepository;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;

import java.util.List;

@Service
@AllArgsConstructor
public class CarBodyTypeEntityManager implements CarBodyTypeEntityService {

    private final CarBodyTypeRepository carBodyTypeRepository;


    @Override
    public CarBodyTypeEntity add(CarBodyTypeEntity carBodyTypeEntity) {
        return null;
    }

    @Override
    public CarBodyTypeEntity update(CarBodyTypeEntity carBodyTypeEntity) {
        return null;
    }

    @Override
    public CarBodyTypeEntity getById(int id) {
        return null;
    }

    @Override
    public void delete(CarBodyTypeEntity carBodyTypeEntity) {

    }

    @Override
    public List<CarBodyTypeEntity> getAll() {
        return null;
    }

    @Override
    public List<AdminEntity> getAllByIsDeletedFalse() {
        return null;
    }

    @Override
    public List<AdminEntity> getAllByIsDeletedTrue() {
        return null;
    }
}
