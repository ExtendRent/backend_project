package src.service.vehicle.features.car.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.car.model.request.CreateCarModelRequest;
import src.controller.vehicle.features.car.model.request.UpdateCarModelRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.repository.vehicle.features.car.model.CarModelRepository;
import src.repository.vehicle.features.common.brand.BrandEntityService;
import src.service.businessrules.abstracts.BaseItemRules;

import java.util.List;

import static src.core.exception.type.AlreadyExistsExceptionType.MODEL_ALREADY_EXISTS;
import static src.core.exception.type.NotFoundExceptionType.MODEL_LIST_NOT_FOUND;

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
