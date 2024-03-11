package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.CarBodyTypeRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemBusinessRulesService;
import source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers.CarBodyTypeEntityServiceImpl;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.BODY_TYPE_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.BODY_TYPE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CarBodyTypeBusinessRules implements BaseItemBusinessRulesService {

    private final CarBodyTypeEntityServiceImpl carBodyTypeEntityServiceImpl;
    private final CarBodyTypeRepository carBodyTypeRepository;

    //--------------------- AUTO FIX METHODS ---------------------
    public CreateCarBodyTypeRequest fixCreateCarBodyTypeRequest(CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        createCarBodyTypeRequest.setCarBodyTypeEntityName(this.fixName(createCarBodyTypeRequest.getCarBodyTypeEntityName()));
        return createCarBodyTypeRequest;
    }

    public UpdateCarBodyTypeRequest fixUpdateCarBodyTypeRequest(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        updateCarBodyTypeRequest.setName(this.fixName(updateCarBodyTypeRequest.getName()));
        return updateCarBodyTypeRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public CreateCarBodyTypeRequest checkCreateCarBodyTypeRequest(CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        this.existsByName(createCarBodyTypeRequest.getCarBodyTypeEntityName());
        return createCarBodyTypeRequest;
    }

    public UpdateCarBodyTypeRequest checkUpdateCarBodyTypeRequest(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        this.existsByName(updateCarBodyTypeRequest.getName());
        updateCarBodyTypeRequest.setId(this.carBodyTypeEntityServiceImpl.getById(updateCarBodyTypeRequest.getId()).getId());
        return updateCarBodyTypeRequest;
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
        if (this.carBodyTypeRepository.existsByName(name)) {
            throw new AlreadyExistsException(BODY_TYPE_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        //Kendisi hariç başka bir isim ile aynı olup olmadığını kontrol etmek için
        if (carBodyTypeRepository.existsByNameAndIdNot(name, id)) {
            throw new AlreadyExistsException(BODY_TYPE_ALREADY_EXISTS);
        }
    }
}
