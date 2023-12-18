package source_files.services.paperWorkServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.UpdateRentalRequest;
import source_files.services.BusinessRules.RentalBusinessRules;
import source_files.services.entityServices.paperWorkEntityManagers.RentalEntityManager;
import source_files.services.paperWorkServices.abstracts.RentalService;

import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private final RentalEntityManager rentalEntityManager;
    private final ModelMapperService modelMapperService;
    private RentalBusinessRules rentalBusinessRules;

    @Override
    public RentalDTO add(AddRentalRequest addRentalRequest) {

        this.rentalBusinessRules.checkRentalRequest(addRentalRequest);

        return null;
    }

    @Override
    public RentalDTO update(UpdateRentalRequest updateRentalRequest) {
        return null;
    }

    @Override
    public RentalDTO getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<RentalDTO> getAll() {
        return null;
    }
}
