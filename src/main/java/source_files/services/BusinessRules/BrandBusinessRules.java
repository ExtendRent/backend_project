package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.dataAccess.vehicleFeaturesRespositories.BrandRespository;
import source_files.exception.DataNotFoundException;

import java.util.List;

import static source_files.exception.NotFoundExceptionType.BRAND_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class BrandBusinessRules implements BaseBusinessRulesService {
    private final BrandRespository brandRespository;

    public void existsByName(String name) {
        if (brandRespository.existsByName(name)) {
            throw new IllegalStateException("This brand already exist");
        }
    }

    public void existById(int brandId) {
        if (!(brandRespository.existsById(brandId))) {
            throw new IllegalStateException("Brand does not exist");
        }
    }

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(BRAND_LIST_NOT_FOUND, "Aradığınız kriterlere uygun marka bulunamadı");
        }
        return list;
    }
}
