package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.dataAccess.vehicleFeaturesRespositories.BrandRespository;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    private final BrandRespository brandRespository;
    public void existsByName(String name){
        if (brandRespository.existsByName(name)){
            throw new IllegalStateException("This brand already exist");
        }
    }
    public void existById(int brandId) {
        if (!(brandRespository.existsById(brandId))){
            throw new IllegalStateException("Brand does not exist");
        }
    }
}
