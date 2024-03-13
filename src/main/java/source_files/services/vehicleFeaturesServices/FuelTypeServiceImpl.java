package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.itemDTOs.FuelTypeDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.FuelTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.CreateFuelTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.UpdateFuelTypeRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.FuelTypeRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.FuelTypeEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.FuelTypeService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelTypeServiceImpl implements FuelTypeService {

    private final FuelTypeEntityService entityService;

    private final FuelTypeRules rules;

    @Override
    public void create(CreateFuelTypeRequest createFuelTypeRequest) {
        entityService.create(createFuelTypeRequest);
    }

    @Override
    public FuelTypeDTO update(UpdateFuelTypeRequest updateFuelTypeRequest) {
        return entityService.update(updateFuelTypeRequest).toModel();
    }

    @Override
    public FuelTypeDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<FuelTypeDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<FuelTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.entityService.delete(this.entityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        FuelTypeEntity fuelTypeEntity = this.entityService.getById(id);
        fuelTypeEntity.setIsDeleted(true);
        fuelTypeEntity.setDeletedAt(LocalDateTime.now());
        this.entityService.update(fuelTypeEntity);
    }

    private List<FuelTypeDTO> mapToDTOList(List<FuelTypeEntity> fuelTypeEntities) {
        rules.checkDataList(fuelTypeEntities);
        return fuelTypeEntities.stream().map(FuelTypeEntity::toModel).toList();
    }
}
