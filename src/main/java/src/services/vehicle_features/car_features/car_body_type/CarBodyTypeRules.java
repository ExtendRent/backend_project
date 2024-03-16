package src.services.vehicle_features.car_features.car_body_type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.carBodyType.CreateCarBodyTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carBodyType.UpdateCarBodyTypeRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.repositories.vehicle_features.CarBodyTypeRepository;
import src.services.businessrules.abstracts.BaseItemRules;

import java.util.List;

import static src.core.exception.exception_types.AlreadyExistsExceptionType.BODY_TYPE_ALREADY_EXISTS;
import static src.core.exception.exception_types.NotFoundExceptionType.BODY_TYPE_LIST_NOT_FOUND;

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
