package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.vehicle.requests.vehicleFeatures.carSegment.CreateCarSegmentRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.carSegment.UpdateCarSegmentRequest;
import source_files.core.exception.AlreadyExistsException;
import source_files.core.exception.DataNotFoundException;
import source_files.repositories.vehicleFeatures.CarSegmentRepository;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemRules;

import java.util.List;

import static source_files.core.exception.exceptionTypes.AlreadyExistsExceptionType.CAR_SEGMENT_ALREADY_EXSISTS;
import static source_files.core.exception.exceptionTypes.NotFoundExceptionType.CAR_SEGMENT_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CarSegmentRules implements BaseItemRules {
    private final CarSegmentRepository repository;

    public CreateCarSegmentRequest fix(CreateCarSegmentRequest createCarSegmentRequest) {
        createCarSegmentRequest.setName(this.fixName(createCarSegmentRequest.getName()));
        return createCarSegmentRequest;
    }

    public UpdateCarSegmentRequest fix(UpdateCarSegmentRequest updateCarSegmentRequest) {
        updateCarSegmentRequest.setName(this.fixName(updateCarSegmentRequest.getName()));
        return updateCarSegmentRequest;
    }

    public void check(CreateCarSegmentRequest createCarSegmentRequest) {
        this.existsByName(createCarSegmentRequest.getName());
    }

    public void check(UpdateCarSegmentRequest updateCarSegmentRequest) {
        existsByNameAndIdNot(updateCarSegmentRequest.getName(), updateCarSegmentRequest.getId());
    }

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(CAR_SEGMENT_LIST_NOT_FOUND);
        }

    }

    @Override
    public String fixName(String name) {
        return name;
    }

    @Override
    public void existsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException(CAR_SEGMENT_ALREADY_EXSISTS);
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        if (repository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new AlreadyExistsException(CAR_SEGMENT_ALREADY_EXSISTS);
        }
    }
}
