package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.AddBrandRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.BrandRespository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;

import java.util.List;

import static source_files.exception.NotFoundExceptionType.BRAND_LIST_NOT_FOUND;
import static source_files.exception.AlreadyExistsExceptionType.NAME_ALREADY_EXISTS;

@AllArgsConstructor
@Service
public class BrandBusinessRules implements BaseBusinessRulesService {
    private final BrandRespository brandRespository;
    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(BRAND_LIST_NOT_FOUND, "Aradığınız kriterlere uygun marka bulunamadı");
        }
        return list;
    }
    public AddBrandRequest fixAddBrandRequest(AddBrandRequest addBrandRequest){
        addBrandRequest.setName(this.fixName(addBrandRequest.getName()));
        return addBrandRequest;
    }
    public AddBrandRequest checkAddBrandRequest(AddBrandRequest addBrandRequest){
        this.existsByName(addBrandRequest.getName());
        return addBrandRequest;
    }
    public UpdateBrandRequest fixUpdateBrandRequest(UpdateBrandRequest updateBrandRequest){
        updateBrandRequest.setName(this.fixName(updateBrandRequest.getName()));
        return updateBrandRequest;
    }
    public UpdateBrandRequest checkUpdateBrandRequest(UpdateBrandRequest updateBrandRequest){
        this.existsByName(updateBrandRequest.getName());
        return updateBrandRequest;
    }
    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }
    //---------------AUTO CHECKING METHODS--------------------------------
    public void existsByName(String name) {
        if (brandRespository.existsByName(name)) {
            throw new AlreadyExistsException(NAME_ALREADY_EXISTS,"This brand name already exist");
        }
    }

//    public void existById(int brandId) {
//        if (!(brandRespository.existsById(brandId))) {
//            throw new IllegalStateException("Brand does not exist");
//        }
//    }

}
