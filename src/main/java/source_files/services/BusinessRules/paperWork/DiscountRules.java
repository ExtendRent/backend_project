package source_files.services.BusinessRules.paperWork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.paperworkRequests.discountRequests.CreateDiscountRequest;
import source_files.data.requests.paperworkRequests.discountRequests.UpdateDiscountRequest;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemBusinessRulesService;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.DISCOUNT_CODE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DiscountRules implements BaseItemBusinessRulesService {
    private final DiscountEntityService discountEntityService;

    public CreateDiscountRequest fix(CreateDiscountRequest createDiscountRequest) {
        createDiscountRequest.setDiscountCode(this.fixName(createDiscountRequest.getDiscountCode().toUpperCase()));
        return createDiscountRequest;
    }

    public UpdateDiscountRequest fix(UpdateDiscountRequest updateDiscountRequest) {
        updateDiscountRequest.setDiscountCode(this.fixName(updateDiscountRequest.getDiscountCode().toUpperCase()));
        return updateDiscountRequest;
    }

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(DISCOUNT_CODE_LIST_NOT_FOUND);
        }
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
