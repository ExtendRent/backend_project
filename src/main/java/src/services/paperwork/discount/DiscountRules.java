package src.services.paperwork.discount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.paperwork.requests.discount.CreateDiscountRequest;
import src.controllers.paperwork.requests.discount.UpdateDiscountRequest;
import src.core.exception.DataNotFoundException;
import src.repositories.paperwork.DiscountRepository;
import src.services.businessrules.abstracts.BaseItemRules;

import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.DISCOUNT_CODE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DiscountRules implements BaseItemRules {
    private final DiscountRepository repository;

    public CreateDiscountRequest fix(CreateDiscountRequest createDiscountRequest) {
        createDiscountRequest.setDiscountCode(this.fixName(createDiscountRequest.getDiscountCode().toUpperCase()));
        return createDiscountRequest;
    }

    public UpdateDiscountRequest fix(UpdateDiscountRequest updateDiscountRequest) {
        updateDiscountRequest.setDiscountCode(this.fixName(updateDiscountRequest.getDiscountCode().toUpperCase()));
        return updateDiscountRequest;
    }

    public void check(CreateDiscountRequest createDiscountRequest) {
        this.existsByName(createDiscountRequest.getDiscountCode());
    }

    public void check(UpdateDiscountRequest updateDiscountRequest) {
        this.existsByNameAndIdNot(updateDiscountRequest.getDiscountCode(), updateDiscountRequest.getId());
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
        repository.existsByDiscountCode(name);
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        repository.existsByDiscountCodeAndIdNot(name, id);
    }
}
