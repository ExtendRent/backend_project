package source_files.services.paperWorkServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.UpdateRentalRequest;
import source_files.dataAccess.paperWorkRepositories.PaymentDetailsRepository;
import source_files.services.BusinessRules.RentalBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;
import source_files.services.entityServices.paperWorkEntityManagers.RentalEntityManager;
import source_files.services.paperWorkServices.abstracts.RentalService;
import source_files.services.vehicleService.abstracts.CarService;

import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private final RentalEntityService rentalEntityService;
    private final ModelMapperService modelMapperService;
    private RentalBusinessRules rentalBusinessRules;
    private final CarService carService;
    private final PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public RentalDTO add(AddRentalRequest addRentalRequest) {
        //todo: indirim işlemleri sonucu totalPrice hesaplama
        this.rentalBusinessRules.checkRentalRequest(addRentalRequest);
        //todo: payment_details oluşturulup save işlemi yapılacak
        RentalEntity rental = modelMapperService.forRequest().map(addRentalRequest,RentalEntity.class);
        addRentalRequest.setStartKilometer(carService.getById(addRentalRequest.getCarId()).getKilometer());
        RentalDTO rentalDTO = this.modelMapperService.forResponse().map(rentalEntityService.add(rental),RentalDTO.class);
        return rentalDTO;
    }

    @Override
    public RentalDTO update(UpdateRentalRequest updateRentalRequest) {
        //todo: ceza işlemleri , indirim işlemleri iptali kontrol edilecek sonuçta da totalPrice güncelleme


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
