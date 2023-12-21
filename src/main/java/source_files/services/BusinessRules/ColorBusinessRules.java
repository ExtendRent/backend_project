package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.AddColorRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.ColorRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;

import java.util.List;

import static source_files.exception.AlreadyExistsExceptionType.COLOR_ALREADY_EXISTS;
import static source_files.exception.NotFoundExceptionType.COLOR_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class ColorBusinessRules implements BaseBusinessRulesService {
    private final ColorRepository colorRepository;

    public AddColorRequest fixAddColorRequest(AddColorRequest addColorRequest){
        addColorRequest.setName(this.fixColorName(addColorRequest.getName()));
        return addColorRequest;
    }

    public AddColorRequest checkAddColorRequest(AddColorRequest addColorRequest) {
        this.existsByName(addColorRequest.getName());
        return addColorRequest;
    }
    //----------------------------------------------------------------------

    private String fixColorName(String colorName){
        return colorName.replace(" ", "").toLowerCase();
    }

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(COLOR_LIST_NOT_FOUND, "Aradığınız kriterlere uygun renk bulunamadı");
        }
        return list;
    }

    //---------------AUTO CHECKING METHODS--------------------------------
    private void existsByName(String name) {
        if (colorRepository.existsByName(name)) {
            throw new AlreadyExistsException(COLOR_ALREADY_EXISTS, "This color already exist");
        }
    }




}


