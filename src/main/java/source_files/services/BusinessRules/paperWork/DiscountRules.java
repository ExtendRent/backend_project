package source_files.services.BusinessRules.paperWork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.paperWork.requests.discount.CreateDiscountRequest;
import source_files.controllers.paperWork.requests.discount.UpdateDiscountRequest;
import source_files.core.exception.DataNotFoundException;
import source_files.repositories.paperWork.DiscountRepository;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemRules;

import java.util.List;

import static source_files.core.exception.exceptionTypes.NotFoundExceptionType.DISCOUNT_CODE_LIST_NOT_FOUND;

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
