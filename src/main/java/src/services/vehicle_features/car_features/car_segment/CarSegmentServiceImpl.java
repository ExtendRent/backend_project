package src.services.vehicle_features.car_features.car_segment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.carSegment.CreateCarSegmentRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carSegment.UpdateCarSegmentRequest;
import src.controllers.vehicle.responses.CarSegmentResponse;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarSegmentEntity;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarSegmentServiceImpl implements CarSegmentService {
    private final CarSegmentEntityService entityService;
    private final CarSegmentRules rules;

    @Override
    public CarSegmentResponse create(CreateCarSegmentRequest createCarSegmentRequest) {
        createCarSegmentRequest = rules.fix(createCarSegmentRequest);
        rules.check(createCarSegmentRequest);
        return entityService.create(createCarSegmentRequest).toModel();
    }

    @Override
    public CarSegmentResponse update(UpdateCarSegmentRequest updateCarSegmentRequest) {
        updateCarSegmentRequest = rules.fix(updateCarSegmentRequest);
        rules.check(updateCarSegmentRequest);
        return entityService.update(updateCarSegmentRequest).toModel();
    }

    @Override
    public CarSegmentResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<CarSegmentResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<CarSegmentResponse> getAllByDeletedState(boolean isDeleted) {
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

    private List<CarSegmentResponse> mapToDTOList(List<CarSegmentEntity> entities) {
        rules.checkDataList(entities);
        return entities.stream().map(CarSegmentEntity::toModel).toList();
    }

}
