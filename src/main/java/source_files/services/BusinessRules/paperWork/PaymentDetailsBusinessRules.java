package source_files.services.BusinessRules.paperWork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.PAYMENT_DETAILS_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class PaymentDetailsBusinessRules implements BaseItemBusinessRulesService {
    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(PAYMENT_DETAILS_LIST_NOT_FOUND);
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
