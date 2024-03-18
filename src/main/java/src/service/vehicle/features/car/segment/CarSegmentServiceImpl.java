package src.service.vehicle.features.car.segment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.controller.vehicle.features.car.segment.requests.CreateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.requests.UpdateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.responses.CarSegmentResponse;
import src.repository.vehicle.features.car.segment.CarSegmentEntity;
import src.repository.vehicle.features.car.segment.CarSegmentEntityService;

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

    @Transactional
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
