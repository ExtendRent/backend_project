package source_files.services.entityServices.paperWorkEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountEntity;
import source_files.dataAccess.paperWorkRepositories.DiscountRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.DISCOUNT_CODE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DiscountEntityManager implements DiscountEntityService {

    private final DiscountRepository discountRepository;

    @Override
    public DiscountEntity create(DiscountEntity discountEntity) {
        return this.discountRepository.save(discountEntity);
    }

    @Override
    public DiscountEntity update(DiscountEntity discountEntity) {
        return this.create(discountEntity);
    }

    @Override
    public void delete(DiscountEntity discountEntity) {
        this.discountRepository.delete(discountEntity);
    }

    @Override
    public DiscountEntity getById(int id) {
        return this.discountRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(DISCOUNT_CODE_NOT_FOUND, "Indirim kodu bulunamadı"));
    }

    @Override
    public DiscountEntity getByDiscountCode(String discountCode) {
        return this.discountRepository.findByDiscountCode(discountCode).orElseThrow(
                () -> new DataNotFoundException(DISCOUNT_CODE_NOT_FOUND, "Indirim kodu bulunamadı")
        );
    }

    @Override
    public List<DiscountEntity> getAll() {
        return this.discountRepository.findAll();
    }

    @Override
    public List<DiscountEntity> getAllByActiveState(boolean isActive) {
        return this.discountRepository.findAllByIsActive(isActive);
    }

    @Override
    public List<DiscountEntity> getAllByDeletedState(boolean isDeleted) {
        return this.discountRepository.findAllByIsDeleted(isDeleted);
    }


}
