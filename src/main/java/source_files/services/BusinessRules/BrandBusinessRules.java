package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.AddBrandRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.BrandRespository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;

import java.util.List;

import static source_files.exception.AlreadyExistsExceptionType.BRAND_ALREADY_EXISTS;
import static source_files.exception.NotFoundExceptionType.BRAND_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class BrandBusinessRules implements BaseItemBusinessRulesService {
    private final BrandRespository brandRespository;

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(BRAND_LIST_NOT_FOUND, "Aradığınız kriterlere uygun marka bulunamadı");
        }
        return list;
    }

    //--------------------- AUTO FIX METHODS ---------------------

    public AddBrandRequest fixAddBrandRequest(AddBrandRequest addBrandRequest) {
        addBrandRequest.setName(this.fixName(addBrandRequest.getName()));
        return addBrandRequest;
    }

    public UpdateBrandRequest fixUpdateBrandRequest(UpdateBrandRequest updateBrandRequest) {
        updateBrandRequest.setName(this.fixName(updateBrandRequest.getName()));
        return updateBrandRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public AddBrandRequest checkAddBrandRequest(AddBrandRequest addBrandRequest) {
        this.existsByName(addBrandRequest.getName());
        return addBrandRequest;
    }

    public UpdateBrandRequest checkUpdateBrandRequest(UpdateBrandRequest updateBrandRequest) {
        this.existsByNameAndIdNot(updateBrandRequest.getName(), updateBrandRequest.getId());
        return updateBrandRequest;
    }


    //----------------------------METHODS--------------------------------

    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }

    public void existsByName(String name) {
        if (brandRespository.existsByName(name)) {
            throw new AlreadyExistsException(BRAND_ALREADY_EXISTS, "This brand name already exist");
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        //Kendisi hariç başka bir isim ile aynı olup olmadığını kontrol etmek için
        if (brandRespository.existsByNameAndIdNot(name, id)) {
            throw new AlreadyExistsException(BRAND_ALREADY_EXISTS, "This brand already exist !");
        }
    }


}
