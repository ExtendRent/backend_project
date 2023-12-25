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

import static source_files.exception.AlreadyExistsExceptionType.COLOR_ALREADY_EXISTS;
import static source_files.exception.NotFoundExceptionType.COLOR_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class ColorBusinessRules implements BaseBusinessRulesService {
    private final ColorRepository colorRepository;
    private final ColorEntityManager colorEntityManager;

    public AddColorRequest fixAddColorRequest(AddColorRequest addColorRequest) {
        addColorRequest.setColorEntityName(this.fixName(addColorRequest.getColorEntityName()));
        return addColorRequest;
    }

    public UpdateColorRequest fixUpdateColorRequest(UpdateColorRequest updateColorRequest) {
        updateColorRequest.setName(this.fixName(updateColorRequest.getName()));
        return updateColorRequest;
    }

    public AddColorRequest checkAddColorRequest(AddColorRequest addColorRequest) {
        this.existsByName(addColorRequest.getColorEntityName());
        return addColorRequest;
    }

    public UpdateColorRequest checkUpdateColorRequest(UpdateColorRequest updateColorRequest) {
        this.existsByName(updateColorRequest.getName());
        updateColorRequest.setId(this.colorEntityManager.getById(updateColorRequest.getId()).getId());
        return updateColorRequest;
    }
    //----------------------------------------------------------------------


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

    //---------------AUTO CHECKING METHODS--------------------------------
    private void existsByName(String name) {
        if (colorRepository.existsByName(name)) {
            throw new AlreadyExistsException(COLOR_ALREADY_EXISTS, "This color already exist");
        }
    }


}


