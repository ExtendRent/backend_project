package source_files.services.BusinessRules.paperWork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.PAYMENT_TYPE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class PaymentTypeBusinessRules implements BaseBusinessRulesService {
    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(PAYMENT_TYPE_LIST_NOT_FOUND, "Aradığınız kriterlere uygun Ödeme şekli bulunamadı");
        }
        return list;
    }

    @Override
    public String fixName(String name) {
        return null;
    }
}
