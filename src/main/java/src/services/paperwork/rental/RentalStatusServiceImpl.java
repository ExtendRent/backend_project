package src.services.paperwork.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.paperwork.responses.RentalStatusResponse;
import src.core.exception.DataNotFoundException;
import src.data.enums.default_data_enums.status.DefaultRentalStatus;
import src.data.models.paperwork_entities.rentalEntities.RentalStatusEntity;
import src.repositories.paperwork.RentalStatusRepository;
import src.services.paperwork.payment.PaymentRules;

import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.RENTAL_STATUS_NOT_FOUND;

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
