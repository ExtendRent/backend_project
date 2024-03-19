package src.service.vehicle.features.common.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.common.brand.request.CreateBrandRequest;
import src.controller.vehicle.features.common.brand.request.UpdateBrandRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.repository.vehicle.features.common.brand.BrandRepository;
import src.service.businessrules.abstracts.BaseItemRules;

import java.util.List;

import static src.core.exception.type.AlreadyExistsExceptionType.BRAND_ALREADY_EXISTS;
import static src.core.exception.type.NotFoundExceptionType.BRAND_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class BrandRules implements BaseItemRules {
    private final BrandRepository brandRepository;

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(BRAND_LIST_NOT_FOUND);
        }

    }

    //--------------------- AUTO FIX METHODS ---------------------

    public CreateBrandRequest fix(CreateBrandRequest createBrandRequest) {
        createBrandRequest.setName(this.fixName(createBrandRequest.getName()));
        return createBrandRequest;
    }

    public UpdateBrandRequest fix(UpdateBrandRequest updateBrandRequest) {
        updateBrandRequest.setName(this.fixName(updateBrandRequest.getName()));
        return updateBrandRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public void check(CreateBrandRequest createBrandRequest) {
        this.existsByName(createBrandRequest.getName());
    }

    public void check(UpdateBrandRequest updateBrandRequest) {
        this.existsByNameAndIdNot(updateBrandRequest.getName(), updateBrandRequest.getId());
    }


    //----------------------------METHODS--------------------------------

    @Override
    public String fixName(String name) {
        return name;
    }

    public void existsByName(String name) {
        if (brandRepository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException(BRAND_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        //Kendisi hariç başka bir isim ile aynı olup olmadığını kontrol etmek için
        if (brandRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new AlreadyExistsException(BRAND_ALREADY_EXISTS);
        }
    }


}
