package source_files.services.entityServices.paperWorkEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountCodeEntity;
import source_files.dataAccess.paperWorkRepositories.DiscountCodeRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountCodeEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.DISCOUNT_CODE_NOT_FOUND;

@Service
@AllArgsConstructor
public class DiscountCodeEntityManager implements DiscountCodeEntityService {

    private final DiscountCodeRepository discountCodeRepository;

    @Override
    public DiscountCodeEntity add(DiscountCodeEntity discountCodeEntity) {
        return this.discountCodeRepository.save(discountCodeEntity);
    }

    @Override
    public DiscountCodeEntity update(DiscountCodeEntity discountCodeEntity) {
        return this.add(discountCodeEntity);
    }

    @Override
    public void delete(DiscountCodeEntity discountCodeEntity) {
        this.discountCodeRepository.delete(discountCodeEntity);
    }

    @Override
    public DiscountCodeEntity getById(int id) {
        return this.discountCodeRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(DISCOUNT_CODE_NOT_FOUND, "Indirim kodu bulunamadı"));
    }

    @Override
    public DiscountCodeEntity getByDiscountCode(String discountCode) {
        return this.discountCodeRepository.findByDiscountCode(discountCode).orElseThrow(
                () -> new DataNotFoundException(DISCOUNT_CODE_NOT_FOUND, "Indirim kodu bulunamadı")
        );
    }

    @Override
    public List<DiscountCodeEntity> getAll() {
        return this.discountCodeRepository.findAll();
    }

    @Override
    public List<DiscountCodeEntity> getAllByIsActiveTrue() {
        return this.discountCodeRepository.findAllByIsActiveTrue();
    }

    @Override
    public List<DiscountCodeEntity> getAllByIsActiveFalse() {
        return this.discountCodeRepository.findAllByIsActiveFalse();
    }

    @Override
    public List<DiscountCodeEntity> getAllByIsDeletedFalse() {
        return this.discountCodeRepository.findAllByIsDeletedFalse();
    }

    @Override
    public List<DiscountCodeEntity> getAllByIsDeletedTrue() {
        return this.discountCodeRepository.findAllByIsDeletedTrue();
    }
}
