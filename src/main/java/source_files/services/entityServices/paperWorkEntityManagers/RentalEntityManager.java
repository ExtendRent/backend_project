package source_files.services.entityServices.paperWorkEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.dataAccess.paperWorkRepositories.RentalRepository;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;

import java.util.List;

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
        return this.rentalRepository.findById(id).orElseThrow();
    }

    @Override
    public List<RentalEntity> getAll() {
        return this.rentalRepository.findAll();
    }

    @Override
    public List<RentalEntity> getAllByIsDeletedFalse() {
        return this.rentalRepository.findAllByIsDeletedFalse();
    }

    @Override
    public List<RentalEntity> getAllByIsDeletedTrue() {
        return this.rentalRepository.findAllByIsDeletedTrue();
    }
}
