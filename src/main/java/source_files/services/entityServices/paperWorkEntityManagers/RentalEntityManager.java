package source_files.services.entityServices.paperWorkEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.dataAccess.paperWorkRepositories.RentalRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.RENTAL_DATA_NOT_FOUND;

@Service
@AllArgsConstructor
public class RentalEntityManager implements RentalEntityService {

    private final RentalRepository rentalRepository;


    @Override
    public RentalEntity add(RentalEntity rentalEntity) {
        return this.rentalRepository.save(rentalEntity);
    }

    @Override
    public RentalEntity update(RentalEntity rentalEntity) {
        return this.add(rentalEntity);
    }

    @Override
    public void delete(RentalEntity rentalEntity) {
        this.rentalRepository.delete(rentalEntity);
    }

    @Override
    public RentalEntity getById(int id) {
        return this.rentalRepository
                .findById(id).orElseThrow(() ->
                        new DataNotFoundException(RENTAL_DATA_NOT_FOUND, "Kiralama kaydı bulunamadı"));
    }

    @Override
    public List<RentalEntity> getAll() {
        return this.rentalRepository.findAll();
    }

    @Override
    public List<RentalEntity> getAllByDeletedState(boolean isDeleted) {
        return this.rentalRepository.findAllByIsDeleted(isDeleted);
    }


}
