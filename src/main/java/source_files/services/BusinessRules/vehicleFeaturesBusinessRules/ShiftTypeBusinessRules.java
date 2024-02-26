package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.SHIFT_TYPE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class ShiftTypeBusinessRules implements BaseBusinessRulesService {
    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(SHIFT_TYPE_LIST_NOT_FOUND);
        }
        return list;
    }

    @Override
    public String fixName(String name) {
        return name;
    }
}
