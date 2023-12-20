package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.dataAccess.vehicleFeaturesRespositories.ColorRepository;

@AllArgsConstructor
@Service
public class ColorBusinessRules {
    private final ColorRepository colorRepository;

    public void existsByName(String name) {
        if (colorRepository.existsByName(name)) {
            throw new IllegalStateException("This color already exist");
        }
    }
}
