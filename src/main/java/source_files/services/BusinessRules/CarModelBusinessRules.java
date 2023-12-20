package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.dataAccess.vehicleFeaturesRespositories.BrandRespository;
import source_files.dataAccess.vehicleFeaturesRespositories.CarModelRepository;

@AllArgsConstructor
@Service
public class CarModelBusinessRules {
    private final CarModelRepository carModelRepository;
    private final BrandRespository brandRespository;

    public void existsByName(String name) {
        if (carModelRepository.existsByName(name)) {
            throw new IllegalStateException("This model already exist");
        }
    }

}
