package source_files.services.BusinessRules.paperWork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.core.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseRules;

import java.util.List;

import static source_files.core.exception.exceptionTypes.NotFoundExceptionType.PAYMENT_DETAILS_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class PaymentDetailsRules implements BaseRules {
    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(PAYMENT_DETAILS_LIST_NOT_FOUND);
        }

    }

    @Override
    public String fixName(String name) {
        return name;
    }
}
