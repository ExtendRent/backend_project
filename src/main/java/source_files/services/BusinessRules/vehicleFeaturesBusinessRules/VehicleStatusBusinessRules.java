package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.VEHICLE_STATUS_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class VehicleStatusBusinessRules implements BaseBusinessRulesService {
    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(VEHICLE_STATUS_LIST_NOT_FOUND);
        }
        
    }

    @Override
    public String fixName(String name) {
        return null;
    }
}
