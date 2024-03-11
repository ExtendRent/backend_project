package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.itemDTOs.CarSegmentDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarSegmentEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.CreateCarSegmentRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.UpdateCarSegmentRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarSegmentBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarSegmentEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.CarSegmentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarSegmentServiceImpl implements CarSegmentService {
    private final CarSegmentEntityService entityService;
    private final CarSegmentBusinessRules rules;

    @Override
    public CarSegmentDTO create(CreateCarSegmentRequest createCarSegmentRequest) {
        return entityService.create(createCarSegmentRequest).toModel();
    }

    @Override
    public CarSegmentDTO update(UpdateCarSegmentRequest updateCarSegmentRequest) {
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
