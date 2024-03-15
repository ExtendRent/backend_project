package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.vehicle.requests.vehicleFeatures.carModel.CreateCarModelRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.carModel.UpdateCarModelRequest;
import source_files.core.exception.AlreadyExistsException;
import source_files.core.exception.DataNotFoundException;
import source_files.repositories.vehicleFeatures.CarModelRepository;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.BrandEntityService;

import java.util.List;

import static source_files.core.exception.exceptionTypes.AlreadyExistsExceptionType.MODEL_ALREADY_EXISTS;
import static source_files.core.exception.exceptionTypes.NotFoundExceptionType.MODEL_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CarModelRules implements BaseItemRules {
    private final CarModelRepository carModelRepository;
    private final BrandEntityService brandEntityService;


    //--------------------- AUTO FIX METHODS ---------------------
    public CreateCarModelRequest fix(CreateCarModelRequest createCarModelRequest) {
        createCarModelRequest.setCarModelEntityName(this.fixName(createCarModelRequest.getCarModelEntityName()));
        return createCarModelRequest;
    }

    public UpdateCarModelRequest fix(UpdateCarModelRequest updateCarModelRequest) {
        updateCarModelRequest.setCarModelEntityName(this.fixName(updateCarModelRequest.getCarModelEntityName()));
        return updateCarModelRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public void check(CreateCarModelRequest createCarModelRequest) {
        this.existsByName(createCarModelRequest.getCarModelEntityName());
        this.checkBrand(createCarModelRequest.getBrandEntityId());
    }


    public void check(UpdateCarModelRequest updateCarModelRequest) {
        this.existsByNameAndIdNot(updateCarModelRequest.getCarModelEntityName(), updateCarModelRequest.getCarModelEntityId());
        this.checkBrand(updateCarModelRequest.getBrandEntityId());
    }


    //----------------------------METHODS--------------------------------

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(MODEL_LIST_NOT_FOUND);
        }
    }

    @Override
    public String fixName(String name) {
        return name;
    }

    @Override
    public void existsByName(String name) {
        if (carModelRepository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException(MODEL_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        if (carModelRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new AlreadyExistsException(MODEL_ALREADY_EXISTS);
        }
    }

    public void checkBrand(int brandId) {
        this.brandEntityService.getById(brandId); //Eğer db de böyle bir brand yok ise brandEntityManager ile hatayı fırlatacak.
    }

}
