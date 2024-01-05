package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarBodyTypeRequests.AddCarBodyTypeRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.CarBodyTypeRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.vehicleFeaturesEntityManagers.CarBodyTypeEntityManager;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.BODY_TYPE_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.BODY_TYPE_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class CarBodyTypeBusinessRules implements BaseItemBusinessRulesService {

    private final CarBodyTypeEntityManager carBodyTypeEntityManager;
    private final CarBodyTypeRepository carBodyTypeRepository;

    //--------------------- AUTO FIX METHODS ---------------------
    public AddCarBodyTypeRequest fixAddCarBodyTypeRequest(AddCarBodyTypeRequest addCarBodyTypeRequest) {
        addCarBodyTypeRequest.setCarBodyTypeEntityName(this.fixName(addCarBodyTypeRequest.getCarBodyTypeEntityName()));
        return addCarBodyTypeRequest;
    }

    public UpdateCarBodyTypeRequest fixUpdateCarBodyTypeRequest(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        updateCarBodyTypeRequest.setName(this.fixName(updateCarBodyTypeRequest.getName()));
        return updateCarBodyTypeRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public AddCarBodyTypeRequest checkAddCarBodyTypeRequest(AddCarBodyTypeRequest addCarBodyTypeRequest) {
        this.existsByName(addCarBodyTypeRequest.getCarBodyTypeEntityName());
        return addCarBodyTypeRequest;
    }

    public UpdateCarBodyTypeRequest checkUpdateCarBodyTypeRequest(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        this.existsByName(updateCarBodyTypeRequest.getName());
        updateCarBodyTypeRequest.setId(this.carBodyTypeEntityManager.getById(updateCarBodyTypeRequest.getId()).getId());
        return updateCarBodyTypeRequest;
    }

    //----------------------------METHODS--------------------------------

    @Override
    public String fixName(String name) {
        return name.replace(" ", "").toLowerCase();
    }

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(BODY_TYPE_LIST_NOT_FOUND, "Aradığınız kriterlere uygun araç kasası bulunamadı");
        }
        return list;
    }

    @Override
    public void existsByName(String name) {
        if (this.carBodyTypeRepository.existsByName(name)) {
            throw new AlreadyExistsException(BODY_TYPE_ALREADY_EXISTS, "This body type already exist");
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        //Kendisi hariç başka bir isim ile aynı olup olmadığını kontrol etmek için
        if (carBodyTypeRepository.existsByNameAndIdNot(name, id)) {
            throw new AlreadyExistsException(BODY_TYPE_ALREADY_EXISTS, "This body type already exist !");
        }
    }
}
