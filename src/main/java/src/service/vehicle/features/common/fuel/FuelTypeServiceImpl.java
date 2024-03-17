package src.service.vehicle.features.common.fuel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.common.fuel.requests.CreateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.requests.UpdateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.responses.FuelTypeResponse;
import src.repository.vehicle.features.common.fuel.FuelTypeEntity;
import src.repository.vehicle.features.common.fuel.FuelTypeEntityService;

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
