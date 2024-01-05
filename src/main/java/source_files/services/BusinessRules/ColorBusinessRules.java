package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.AddColorRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.ColorRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.vehicleFeaturesEntityManagers.ColorEntityManager;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.COLOR_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.COLOR_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class ColorBusinessRules implements BaseItemBusinessRulesService {
    private final ColorRepository colorRepository;
    private final ColorEntityManager colorEntityManager;

    //--------------------- AUTO FIX METHODS ---------------------
    public AddColorRequest fixAddColorRequest(AddColorRequest addColorRequest) {
        addColorRequest.setColorEntityName(this.fixName(addColorRequest.getColorEntityName()));
        return addColorRequest;
    }

    public UpdateColorRequest fixUpdateColorRequest(UpdateColorRequest updateColorRequest) {
        updateColorRequest.setName(this.fixName(updateColorRequest.getName()));
        return updateColorRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public AddColorRequest checkAddColorRequest(AddColorRequest addColorRequest) {
        this.existsByName(addColorRequest.getColorEntityName());
        return addColorRequest;
    }

    public UpdateColorRequest checkUpdateColorRequest(UpdateColorRequest updateColorRequest) {

        this.existsByNameAndIdNot(updateColorRequest.getName(), updateColorRequest.getId());
        updateColorRequest.setId(this.colorEntityManager.getById(updateColorRequest.getId()).getId());
        return updateColorRequest;
    }


    //----------------------------METHODS--------------------------------

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(COLOR_LIST_NOT_FOUND, "Aradığınız kriterlere uygun renk bulunamadı");
        }
        return list;
    }

    @Override
    public String fixName(String name) {
        return name.replace(" ", "").toLowerCase();
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        //Kendisi hariç başka bir rengin ismi ile aynı olup olmadığını kontrol etmek için
        if (colorRepository.existsByNameAndIdNot(name, id)) {
            throw new AlreadyExistsException(COLOR_ALREADY_EXISTS, "This color already exist !");
        }
    }

    @Override
    public void existsByName(String name) {
        if (colorRepository.existsByName(name)) {
            throw new AlreadyExistsException(COLOR_ALREADY_EXISTS, "This color already exist :)");
        }
    }


}


