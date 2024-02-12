package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.enums.Status.DefaultVehicleStatus;
import source_files.data.models.vehicleEntities.vehicleFeatures.VehicleStatusEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.VehicleStatusRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.VehicleStatusEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.VEHICLE_STATUS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class VehicleStatusEntityManager implements VehicleStatusEntityService {

    private final VehicleStatusRepository vehicleStatusRepository;

    @Override
    public VehicleStatusEntity create(VehicleStatusEntity vehicleStatusEntity) {
        return vehicleStatusRepository.save(vehicleStatusEntity);
    }

    @Override
    public VehicleStatusEntity update(VehicleStatusEntity vehicleStatusEntity) {
        return vehicleStatusRepository.save(vehicleStatusEntity);
    }

    @Override
    public VehicleStatusEntity getById(int id) {
        return vehicleStatusRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(VEHICLE_STATUS_NOT_FOUND, "Araç durumu bulunamadı."));
    }

    @Override
    public List<VehicleStatusEntity> getAll() {
        return vehicleStatusRepository.findAll();
    }

    @Override
    public VehicleStatusEntity getByStatus(DefaultVehicleStatus status) {
        return vehicleStatusRepository.findByVehicleStatus(status)
                .orElseThrow(() -> new DataNotFoundException(VEHICLE_STATUS_NOT_FOUND, "Araç durumu bulunamadı."));
    }

    @Override
    public void delete(VehicleStatusEntity vehicleStatusEntity) {
        vehicleStatusRepository.delete(vehicleStatusEntity);
    }
}
