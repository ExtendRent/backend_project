package source_files.services.entityServices.paperWorkEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountEntity;
import source_files.data.requests.paperworkRequests.discountRequests.CreateDiscountRequest;
import source_files.data.requests.paperworkRequests.discountRequests.UpdateDiscountRequest;
import source_files.dataAccess.paperWorkRepositories.DiscountRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.DISCOUNT_CODE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DiscountEntityServiceImpl implements DiscountEntityService {

    private final DiscountRepository repository;

    @Override
    public DiscountEntity create(CreateDiscountRequest createDiscountRequest) {
        DiscountEntity discountEntity = DiscountEntity.discountBuilder()
                .discountCode(createDiscountRequest.getDiscountCode())
                .discountPercentage(createDiscountRequest.getDiscountPercentage())
                .build();
        return repository.save(discountEntity);
    }

    @Override
    public DiscountEntity update(UpdateDiscountRequest updateDiscountRequest) {
        DiscountEntity discountEntity = DiscountEntity.discountBuilder()
                .id(updateDiscountRequest.getId())
                .discountCode(updateDiscountRequest.getDiscountCode())
                .discountPercentage(updateDiscountRequest.getDiscountPercentage())
                .isActive(updateDiscountRequest.isActive())
                .build();
        return repository.save(discountEntity);
    }

    @Override
    public DiscountEntity update(DiscountEntity discountEntity) {
        return repository.save(discountEntity);
    }

    @Override
    public void delete(DiscountEntity discountEntity) {
        repository.delete(discountEntity);
    }

    @Override
    public DiscountEntity getById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundException(DISCOUNT_CODE_NOT_FOUND));
    }

    @Override
    public DiscountEntity getByDiscountCode(String discountCode) {
        return repository.findByDiscountCode(discountCode).orElseThrow(
                () -> new DataNotFoundException(DISCOUNT_CODE_NOT_FOUND)
        );
    }

    @Override
    public List<DiscountEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<DiscountEntity> getAllByActiveState(boolean isActive) {
        return repository.findAllByIsActive(isActive);
    }

    @Override
    public List<DiscountEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }


}
