package source_files.services.paperWorkServices.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.paperWorkDTOs.RentalStatusDTO;
import source_files.data.enums.defaultDataEnums.Status.DefaultRentalStatus;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalStatusEntity;
import source_files.dataAccess.paperWorkRepositories.RentalStatusRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.paperWork.PaymentBusinessRules;
import source_files.services.paperWorkServices.abstracts.RentalStatusService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.RENTAL_STATUS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RentalStatusManager implements RentalStatusService {
    private final RentalStatusRepository repository;
    private final PaymentBusinessRules rules;

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
    public List<RentalStatusDTO> getAll() {
        List<RentalStatusEntity> entities = repository.findAll();
        rules.checkDataList(entities);
        return entities.stream().map(RentalStatusEntity::toModel).toList();
    }
}
