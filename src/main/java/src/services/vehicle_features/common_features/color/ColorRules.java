package src.services.vehicle_features.common_features.color;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.color.CreateColorRequest;
import src.controllers.vehicle.requests.vehicleFeatures.color.UpdateColorRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.repositories.vehicle_features.ColorRepository;
import src.services.businessrules.abstracts.BaseItemRules;

import java.util.List;

import static src.core.exception.exception_types.AlreadyExistsExceptionType.COLOR_ALREADY_EXISTS;
import static src.core.exception.exception_types.NotFoundExceptionType.COLOR_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class ColorRules implements BaseItemRules {
    private final ColorRepository colorRepository;
    private final ColorEntityServiceImpl colorEntityServiceImpl;

    //--------------------- AUTO FIX METHODS ---------------------
    public CreateColorRequest fix(CreateColorRequest createColorRequest) {
        createColorRequest.setColorEntityName(this.fixName(createColorRequest.getColorEntityName()));
        return createColorRequest;
    }

    public UpdateColorRequest fix(UpdateColorRequest updateColorRequest) {
        updateColorRequest.setName(this.fixName(updateColorRequest.getName()));
        return updateColorRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public void check(CreateColorRequest createColorRequest) {
        this.existsByName(createColorRequest.getColorEntityName());
    }

    public void check(UpdateColorRequest updateColorRequest) {
        this.existsByNameAndIdNot(updateColorRequest.getName(), updateColorRequest.getId());
        updateColorRequest.setId(this.colorEntityServiceImpl.getById(updateColorRequest.getId()).getId());
    }


    //----------------------------METHODS--------------------------------

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(COLOR_LIST_NOT_FOUND);
        }
    }

    @Override
    public String fixName(String name) {
        return name;
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        //Kendisi hariç başka bir rengin ismi ile aynı olup olmadığını kontrol etmek için
        if (colorRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new AlreadyExistsException(COLOR_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByName(String name) {
        if (colorRepository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException(COLOR_ALREADY_EXISTS);
        }
    }


}


