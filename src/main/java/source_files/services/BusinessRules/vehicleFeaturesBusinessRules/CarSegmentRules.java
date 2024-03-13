package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.CreateCarSegmentRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.UpdateCarSegmentRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.CarSegmentRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemRules;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.CAR_SEGMENT_ALREADY_EXSISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.CAR_SEGMENT_LIST_NOT_FOUND;

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
