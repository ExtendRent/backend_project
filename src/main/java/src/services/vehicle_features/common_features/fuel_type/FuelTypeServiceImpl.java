package src.services.vehicle_features.common_features.fuel_type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.fuelType.CreateFuelTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.fuelType.UpdateFuelTypeRequest;
import src.controllers.vehicle.responses.FuelTypeResponse;
import src.data.models.vehicle_entities.vehicle_features.FuelTypeEntity;

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
    public FuelTypeResponse update(UpdateFuelTypeRequest updateFuelTypeRequest) {
        return entityService.update(updateFuelTypeRequest).toModel();
    }

    @Override
    public FuelTypeResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<FuelTypeResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<FuelTypeResponse> getAllByDeletedState(boolean isDeleted) {
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

    private List<FuelTypeResponse> mapToDTOList(List<FuelTypeEntity> fuelTypeEntities) {
        rules.checkDataList(fuelTypeEntities);
        return fuelTypeEntities.stream().map(FuelTypeEntity::toModel).toList();
    }
}
