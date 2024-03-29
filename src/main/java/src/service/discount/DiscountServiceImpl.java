package src.service.discount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.controller.discount.request.CreateDiscountRequest;
import src.controller.discount.request.UpdateDiscountRequest;
import src.controller.discount.response.DiscountResponse;
import src.repository.discount.DiscountEntity;
import src.repository.discount.DiscountEntityService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountEntityService entityService;
    private final DiscountRules rules;

    @Override
    public void create(CreateDiscountRequest createDiscountRequest) {
        createDiscountRequest = rules.fix(createDiscountRequest);
        rules.check(createDiscountRequest);
        entityService.create(createDiscountRequest);
    }

    @Override
    public DiscountResponse update(UpdateDiscountRequest updateDiscountRequest) {
        updateDiscountRequest = rules.fix(updateDiscountRequest);
        rules.check(updateDiscountRequest);
        return entityService.update(updateDiscountRequest).toModel();
    }

    @Override
    public DiscountResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public DiscountResponse getByDiscountCode(String discountCode) {
        return entityService.getByDiscountCode(discountCode).toModel();
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            entityService.delete(entityService.getById(id));
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        DiscountEntity discountCode = entityService.getById(id);
        discountCode.setIsDeleted(true);
        discountCode.setDeletedAt(LocalDateTime.now());
        entityService.update(discountCode);
    }

    @Override
    public List<DiscountResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Transactional
    @Override
    public List<DiscountResponse> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public List<DiscountResponse> getAllByActiveState(boolean isActive) {
        return mapToDTOList(entityService.getAllByActiveState(isActive));
    }

    private List<DiscountResponse> mapToDTOList(List<DiscountEntity> discounts) {
        rules.checkDataList(discounts);
        return discounts.stream().map(DiscountEntity::toModel).toList();
    }

}
