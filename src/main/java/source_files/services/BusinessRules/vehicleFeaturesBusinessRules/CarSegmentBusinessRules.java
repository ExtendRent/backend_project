package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.CAR_SEGMENT_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CarSegmentBusinessRules implements BaseItemBusinessRulesService {
    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(CAR_SEGMENT_LIST_NOT_FOUND);
        }
        
    }

    @Override
    public String fixName(String name) {
        return null;
    }

    @Override
    public void existsByName(String name) {
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {

    }
}
