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
    public CreateColorRequest fixCreateColorRequest(CreateColorRequest createColorRequest) {
        createColorRequest.setColorEntityName(this.fixName(createColorRequest.getColorEntityName()));
        return createColorRequest;
    }

    public UpdateColorRequest fixUpdateColorRequest(UpdateColorRequest updateColorRequest) {
        updateColorRequest.setName(this.fixName(updateColorRequest.getName()));
        return updateColorRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public CreateColorRequest checkCreateColorRequest(CreateColorRequest createColorRequest) {
        this.existsByName(createColorRequest.getColorEntityName());
        return createColorRequest;
    }

    public UpdateColorRequest checkUpdateColorRequest(UpdateColorRequest updateColorRequest) {

        this.existsByNameAndIdNot(updateColorRequest.getName(), updateColorRequest.getId());
        updateColorRequest.setId(this.colorEntityServiceImpl.getById(updateColorRequest.getId()).getId());
        return updateColorRequest;
    }


    //----------------------------METHODS--------------------------------

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(COLOR_LIST_NOT_FOUND);
        }
        return list;
    }

    @Override
    public String fixName(String name) {
        return name;
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        //Kendisi hariç başka bir rengin ismi ile aynı olup olmadığını kontrol etmek için
        if (colorRepository.existsByNameAndIdNot(name, id)) {
            throw new AlreadyExistsException(COLOR_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByName(String name) {
        if (colorRepository.existsByName(name)) {
            throw new AlreadyExistsException(COLOR_ALREADY_EXISTS);
        }
    }


}


