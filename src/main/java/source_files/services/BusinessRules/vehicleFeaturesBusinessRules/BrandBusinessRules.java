package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.BrandRespository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.BRAND_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.BRAND_LIST_NOT_FOUND;

@RequiredArgsConstructor
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

    public CreateBrandRequest fixCreateBrandRequest(CreateBrandRequest createBrandRequest) {
        createBrandRequest.setName(this.fixName(createBrandRequest.getName()));
        return createBrandRequest;
    }

    public UpdateBrandRequest fixUpdateBrandRequest(UpdateBrandRequest updateBrandRequest) {
        updateBrandRequest.setName(this.fixName(updateBrandRequest.getName()));
        return updateBrandRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public CreateBrandRequest checkCreateBrandRequest(CreateBrandRequest createBrandRequest) {
        this.existsByName(createBrandRequest.getName());
        return createBrandRequest;
    }

    public UpdateBrandRequest checkUpdateBrandRequest(UpdateBrandRequest updateBrandRequest) {
        this.existsByNameAndIdNot(updateBrandRequest.getName(), updateBrandRequest.getId());
        return updateBrandRequest;
    }


    //----------------------------METHODS--------------------------------

    @Override
    public String fixName(String name) {
        return name;
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
