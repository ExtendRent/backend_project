package src.service.vehicle.features.car.segment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.car.segment.request.CreateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.request.UpdateCarSegmentRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.repository.vehicle.features.car.segment.CarSegmentRepository;
import src.service.businessrules.abstracts.BaseItemRules;

import java.util.List;

import static src.core.exception.type.AlreadyExistsExceptionType.CAR_SEGMENT_ALREADY_EXSISTS;
import static src.core.exception.type.NotFoundExceptionType.CAR_SEGMENT_LIST_NOT_FOUND;

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
