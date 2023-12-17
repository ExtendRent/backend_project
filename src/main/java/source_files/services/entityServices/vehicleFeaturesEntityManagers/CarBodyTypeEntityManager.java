package source_files.services.entityServices.vehicleFeaturesEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.CarBodyTypeRepository;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;

import java.util.List;

@Service
@AllArgsConstructor
public class CarBodyTypeEntityManager implements CarBodyTypeEntityService {

    private final CarBodyTypeRepository carBodyTypeRepository;

    @Override
    public CarBodyTypeEntity addCarBodyType(CarBodyTypeEntity carBodyTypeEntity) {
        return this.carBodyTypeRepository.save(carBodyTypeEntity);
    }

    @Override
    public CarBodyTypeEntity updateCarBodyType(CarBodyTypeEntity carBodyTypeEntity) {
        return this.carBodyTypeRepository.save(carBodyTypeEntity);
    }

    @Override
    public CarBodyTypeEntity getCarBodyTypeById(int id) {
        return this.carBodyTypeRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteCarBodyType(CarBodyTypeEntity carBodyTypeEntity) {
        this.carBodyTypeRepository.delete(carBodyTypeEntity);
    }

    @Override
    public List<CarBodyTypeEntity> getAllCarBodyType() {
        return this.carBodyTypeRepository.findAll();
    }
}
