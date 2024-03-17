package src.service.paperwork.payment.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.core.exception.DataNotFoundException;
import src.service.businessrules.abstracts.BaseRules;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.PAYMENT_DETAILS_LIST_NOT_FOUND;

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
