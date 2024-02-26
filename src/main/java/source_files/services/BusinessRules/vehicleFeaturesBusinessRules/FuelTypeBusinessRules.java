package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.FUEL_TYPE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class FuelTypeBusinessRules implements BaseBusinessRulesService {
    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(FUEL_TYPE_LIST_NOT_FOUND);
        }
        return list;
    }

    @Override
    public String fixName(String name) {
        return name;
    }
}
