package source_files.services.vehicleFeatures;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.vehicle.dtos.CarSegmentDTO;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarSegmentRequests.CreateCarSegmentRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarSegmentRequests.UpdateCarSegmentRequest;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarSegmentEntity;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarSegmentRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarSegmentEntityService;
import source_files.services.vehicleFeatures.abstracts.CarSegmentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarSegmentServiceImpl implements CarSegmentService {
    private final CarSegmentEntityService entityService;
    private final CarSegmentRules rules;

    @Override
    public CarSegmentDTO create(CreateCarSegmentRequest createCarSegmentRequest) {
        createCarSegmentRequest = rules.fix(createCarSegmentRequest);
        rules.check(createCarSegmentRequest);
        return entityService.create(createCarSegmentRequest).toModel();
    }

    @Override
    public CarSegmentDTO update(UpdateCarSegmentRequest updateCarSegmentRequest) {
        updateCarSegmentRequest = rules.fix(updateCarSegmentRequest);
        rules.check(updateCarSegmentRequest);
        return entityService.update(updateCarSegmentRequest).toModel();
    }

    @Override
    public CarSegmentDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<CarSegmentDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<CarSegmentDTO> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            entityService.delete(entityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        CarSegmentEntity carSegment = entityService.getById(id);
        carSegment.setDeletedAt(LocalDateTime.now());
        carSegment.setIsDeleted(true);
        entityService.update(carSegment);
    }

    private List<CarSegmentDTO> mapToDTOList(List<CarSegmentEntity> entities) {
        rules.checkDataList(entities);
        return entities.stream().map(CarSegmentEntity::toModel).toList();
    }

}
