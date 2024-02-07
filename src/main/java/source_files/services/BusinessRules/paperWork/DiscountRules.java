package source_files.services.BusinessRules.paperWork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.paperworkRequests.discountRequests.CreateDiscountRequest;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.DISCOUNT_CODE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DiscountRules implements BaseItemBusinessRulesService {

    public CreateDiscountRequest fixDiscountRequest(CreateDiscountRequest createDiscountRequest) {
        createDiscountRequest.setDiscountCode(this.fixName(createDiscountRequest.getDiscountCode().toUpperCase()));
        return createDiscountRequest;
    }

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(DISCOUNT_CODE_LIST_NOT_FOUND, "Aradığınız kriterlere uygun indirim kodu bulunamadı");
        }
        return list;
    }

    @Override
    public String fixName(String name) {
        return name.trim().toUpperCase();
    }

    @Override
    public void existsByName(String name) {

    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {

    }
}
