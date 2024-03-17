package src.service.vehicle.features.car.body;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.car.body.requests.CreateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.requests.UpdateCarBodyTypeRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.repository.vehicle.features.car.body.CarBodyTypeRepository;
import src.service.businessrules.abstracts.BaseItemRules;

import java.util.List;

import static src.core.exception.type.AlreadyExistsExceptionType.BODY_TYPE_ALREADY_EXISTS;
import static src.core.exception.type.NotFoundExceptionType.BODY_TYPE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CarBodyTypeRules implements BaseItemRules {
    private final CarBodyTypeRepository carBodyTypeRepository;

    //--------------------- AUTO FIX METHODS ---------------------
    public CreateCarBodyTypeRequest fix(CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        createCarBodyTypeRequest.setCarBodyTypeEntityName(this.fixName(createCarBodyTypeRequest.getCarBodyTypeEntityName()));
        return createCarBodyTypeRequest;
    }

    public UpdateCarBodyTypeRequest fix(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        updateCarBodyTypeRequest.setName(this.fixName(updateCarBodyTypeRequest.getName()));
        return updateCarBodyTypeRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public void check(CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        this.existsByName(createCarBodyTypeRequest.getCarBodyTypeEntityName());
    }

    public void check(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        this.existsByName(updateCarBodyTypeRequest.getName());
    }

    //----------------------------METHODS--------------------------------

    @Override
    public String fixName(String name) {
        return name.replace(" ", "").toLowerCase();
    }

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(BODY_TYPE_LIST_NOT_FOUND);
        }

    }

    @Override
    public void existsByName(String name) {
        if (this.carBodyTypeRepository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException(BODY_TYPE_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        //Kendisi hariç başka bir isim ile aynı olup olmadığını kontrol etmek için
        if (carBodyTypeRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new AlreadyExistsException(BODY_TYPE_ALREADY_EXISTS);
        }
    }
}
