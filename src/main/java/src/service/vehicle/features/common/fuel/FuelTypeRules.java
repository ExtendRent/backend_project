package src.service.vehicle.features.common.fuel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.core.exception.DataNotFoundException;
import src.service.businessrules.abstracts.BaseRules;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.FUEL_TYPE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class FuelTypeRules implements BaseRules {
    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(FUEL_TYPE_LIST_NOT_FOUND);
        }

    }

    @Override
    public String fixName(String name) {
        return name;
    }
}
