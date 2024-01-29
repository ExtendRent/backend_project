package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.FuelTypeDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.FuelTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.CreateFuelTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.UpdateFuelTypeRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.FuelTypeBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.FuelTypeEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.FuelTypeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuelTypeManager implements FuelTypeService {

    private final FuelTypeEntityService fuelTypeEntityService;
    private final ModelMapperService mapper;

    private final FuelTypeBusinessRules rules;

    @Override
    public void create(CreateFuelTypeRequest createFuelTypeRequest) {
        fuelTypeEntityService.create(mapper.forRequest()
                .map(createFuelTypeRequest, FuelTypeEntity.class));
    }

    @Override
    public FuelTypeDTO update(UpdateFuelTypeRequest updateFuelTypeRequest) {
        return mapper.forResponse().map(fuelTypeEntityService.update(mapper.forRequest()
                .map(updateFuelTypeRequest, FuelTypeEntity.class)), FuelTypeDTO.class);
    }

    @Override
    public FuelTypeDTO getById(int id) {
        return mapper.forResponse().map(fuelTypeEntityService.getById(id), FuelTypeDTO.class);
    }

    @Override
    public List<FuelTypeDTO> getAll() {
        return rules.checkDataList(fuelTypeEntityService.getAll())
                .stream().map(fuelType -> mapper.forResponse().map(fuelType, FuelTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FuelTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(fuelTypeEntityService.getAllByDeletedState(isDeleted))
                .stream().map(fuelType -> mapper.forResponse().map(fuelType, FuelTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.fuelTypeEntityService.delete(this.fuelTypeEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        FuelTypeEntity fuelTypeEntity = this.fuelTypeEntityService.getById(id);
        fuelTypeEntity.setIsDeleted(true);
        fuelTypeEntity.setDeletedAt(LocalDateTime.now());
        this.fuelTypeEntityService.update(fuelTypeEntity);
    }
}
