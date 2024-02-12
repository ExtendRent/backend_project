package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.CarSegmentDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarSegmentEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.CreateCarSegmentRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.UpdateCarSegmentRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarSegmentBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarSegmentEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.CarSegmentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarSegmentManager implements CarSegmentService {
    private final ModelMapperService mapper;
    private final CarSegmentEntityService entityService;
    private final CarSegmentBusinessRules rules;

    @Override
    public CarSegmentDTO create(CreateCarSegmentRequest createCarSegmentRequest) {
        return mapToDTO(entityService.create(mapper.forRequest().map(createCarSegmentRequest, CarSegmentEntity.class)));
    }

    @Override
    public CarSegmentDTO update(UpdateCarSegmentRequest updateCarSegmentRequest) {
        return mapToDTO(entityService.update(mapper.forRequest().map(updateCarSegmentRequest, CarSegmentEntity.class)));
    }

    @Override
    public CarSegmentDTO getById(int id) {
        return mapToDTO(entityService.getById(id));
    }

    @Override
    public List<CarSegmentDTO> getAll() {
        return rules.checkDataList(entityService.getAll())
                .stream().map(segment -> mapper.forResponse()
                        .map(segment, CarSegmentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarSegmentDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(entityService.getAllByDeletedState(isDeleted))
                .stream().map(segment -> mapper.forResponse(
                ).map(segment, CarSegmentDTO.class)).collect(Collectors.toList());
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

    private CarSegmentDTO mapToDTO(CarSegmentEntity carSegmentEntity) {
        return mapper.forResponse().map(carSegmentEntity, CarSegmentDTO.class);
    }
}
