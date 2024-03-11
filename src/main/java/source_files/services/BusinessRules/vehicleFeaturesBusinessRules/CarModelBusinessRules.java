package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.CreateCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.CarModelRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemBusinessRulesService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.BrandEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.BODY_TYPE_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.CAR_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CarModelBusinessRules implements BaseItemBusinessRulesService {
    private final CarModelRepository carModelRepository;
    private final BrandEntityService brandEntityService;


    //--------------------- AUTO FIX METHODS ---------------------
    public CreateCarModelRequest fixCreateCarModelRequest(CreateCarModelRequest createCarModelRequest) {
        createCarModelRequest.setCarModelEntityName(this.fixName(createCarModelRequest.getCarModelEntityName()));
        return createCarModelRequest;
    }

    public UpdateCarModelRequest fixUpdateCarModelRequest(UpdateCarModelRequest updateCarModelRequest) {
        updateCarModelRequest.setCarModelEntityName(this.fixName(updateCarModelRequest.getCarModelEntityName()));
        return updateCarModelRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public CreateCarModelRequest checkCreateCarModelRequest(CreateCarModelRequest createCarModelRequest) {
        this.existsByName(createCarModelRequest.getCarModelEntityName());
        this.checkBrand(createCarModelRequest.getBrandEntityId());
        return createCarModelRequest;
    }


    public UpdateCarModelRequest checkUpdateCarModelRequest(UpdateCarModelRequest updateCarModelRequest) {
        this.existsByName(updateCarModelRequest.getCarModelEntityName());
        this.checkBrand(updateCarModelRequest.getBrandEntityId());
        return updateCarModelRequest;
    }


    //----------------------------METHODS--------------------------------

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(CAR_LIST_NOT_FOUND);
        }
        
    }

    @Override
    public String fixName(String name) {
        return name;
    }

    @Override
    public void existsByName(String name) {
        if (carModelRepository.existsByName(name)) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        if (carModelRepository.existsByNameAndIdNot(name, id)) {
            throw new AlreadyExistsException(BODY_TYPE_ALREADY_EXISTS);
        }
    }

    public void checkBrand(int brandId) {
        this.brandEntityService.getById(brandId); //Eğer db de böyle bir brand yok ise brandEntityManager ile hatayı fırlatacak.
    }

}
