package src.service.rental.status;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.controller.rental.responses.RentalStatusResponse;
import src.core.exception.DataNotFoundException;
import src.repository.rental.status.RentalStatusEntity;
import src.repository.rental.status.RentalStatusRepository;
import src.service.payment.PaymentRules;
import src.service.rental.status.model.DefaultRentalStatus;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.RENTAL_STATUS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RentalStatusServiceImpl implements RentalStatusService {
    private final RentalStatusRepository repository;
    private final PaymentRules rules;

    @Override
    public RentalStatusEntity create(RentalStatusEntity rentalStatusEntity) {
        rentalStatusEntity.setId(0);
        return repository.save(rentalStatusEntity);
    }

    @Override
    public RentalStatusEntity update(RentalStatusEntity rentalStatusEntity) {
        return repository.save(rentalStatusEntity);
    }

    @Override
    public void delete(RentalStatusEntity rentalStatusEntity) {
        repository.delete(rentalStatusEntity);
    }

    @Override
    public RentalStatusEntity getById(int id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException(RENTAL_STATUS_NOT_FOUND));
    }

    @Transactional
    @Override
    public RentalStatusEntity getByStatus(DefaultRentalStatus status) {
        return repository.findByStatus(status).orElseThrow(() -> new DataNotFoundException(RENTAL_STATUS_NOT_FOUND));
    }


    @Override
    public List<RentalStatusResponse> getAll() {
        List<RentalStatusEntity> entities = repository.findAll();
        rules.checkDataList(entities);
        return entities.stream().map(RentalStatusEntity::toModel).toList();
    }
}
