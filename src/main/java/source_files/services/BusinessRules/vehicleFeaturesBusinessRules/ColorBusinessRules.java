package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.ColorRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemBusinessRulesService;
import source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers.ColorEntityServiceImpl;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.COLOR_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.COLOR_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class ColorBusinessRules implements BaseItemBusinessRulesService {
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


