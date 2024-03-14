package source_files.services.paperWork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.paperWork.dtos.DiscountDTO;
import source_files.controllers.paperWork.requests.discountRequests.CreateDiscountRequest;
import source_files.controllers.paperWork.requests.discountRequests.UpdateDiscountRequest;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountEntity;
import source_files.services.BusinessRules.paperWork.DiscountRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountEntityService;
import source_files.services.paperWork.abstracts.DiscountService;

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
    public DiscountDTO update(UpdateDiscountRequest updateDiscountRequest) {
        updateDiscountRequest = rules.fix(updateDiscountRequest);
        rules.check(updateDiscountRequest);
        return entityService.update(updateDiscountRequest).toModel();
    }

    @Override
    public DiscountDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public DiscountDTO getByDiscountCode(String discountCode) {
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
    public List<DiscountDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<DiscountDTO> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public List<DiscountDTO> getAllByActiveState(boolean isActive) {
        return mapToDTOList(entityService.getAllByActiveState(isActive));
    }

    private List<DiscountDTO> mapToDTOList(List<DiscountEntity> discounts) {
        rules.checkDataList(discounts);
        return discounts.stream().map(DiscountEntity::toModel).toList();
    }

}
