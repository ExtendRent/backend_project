package src.service.vehicle.features.common.status;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.common.status.requests.CreateVehicleStatusRequest;
import src.controller.vehicle.features.common.status.requests.UpdateVehicleStatusRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.repository.vehicle.features.common.status.VehicleStatusRepository;
import src.service.businessrules.abstracts.BaseItemRules;

import java.util.List;

import static src.core.exception.type.AlreadyExistsExceptionType.VEHICLE_STATUS_ALREADY_EXISTS;
import static src.core.exception.type.NotFoundExceptionType.VEHICLE_STATUS_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class VehicleStatusRules implements BaseItemRules {

    private final VehicleStatusRepository repository;

    public CreateVehicleStatusRequest fix(CreateVehicleStatusRequest createVehicleStatusRequest) {
        createVehicleStatusRequest.setName(fixName(createVehicleStatusRequest.getName()));
        return createVehicleStatusRequest;
    }

    public UpdateVehicleStatusRequest fix(UpdateVehicleStatusRequest updateVehicleStatusRequest) {
        updateVehicleStatusRequest.setName(fixName(updateVehicleStatusRequest.getName()));
        return updateVehicleStatusRequest;
    }

    public void check(CreateVehicleStatusRequest createVehicleStatusRequest) {
        existsByName(createVehicleStatusRequest.getName());
    }

    public void check(UpdateVehicleStatusRequest updateVehicleStatusRequest) {
        existsByNameAndIdNot(updateVehicleStatusRequest.getName(), updateVehicleStatusRequest.getId());
    }

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(VEHICLE_STATUS_LIST_NOT_FOUND);
        }

    }

    @Override
    public String fixName(String name) {
        return name;
    }


    @Override
    public void existsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException(VEHICLE_STATUS_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        if (repository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new AlreadyExistsException(VEHICLE_STATUS_ALREADY_EXISTS);
        }
    }
}
