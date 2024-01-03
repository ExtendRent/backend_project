package source_files.services.paperWorkServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.UpdateRentalRequest;
import source_files.dataAccess.paperWorkRepositories.PaymentDetailsRepository;
import source_files.dataAccess.paperWorkRepositories.PaymentTypeEntityRepository;
import source_files.services.BusinessRules.RentalBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;
import source_files.services.paperWorkServices.abstracts.RentalService;
import source_files.services.vehicleService.abstracts.CarService;

import java.util.List;

import static source_files.data.types.ItemType.RENTAL;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private final RentalEntityService rentalEntityService;
    private final ModelMapperService modelMapperService;
    private final CarService carService;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final PaymentTypeEntityRepository paymentTypeEntityRepository;
    private RentalBusinessRules rentalBusinessRules;

    @Override
    public RentalDTO add(AddRentalRequest addRentalRequest) {
        //todo: indirim işlemleri sonucu totalPrice hesaplama


        rentalBusinessRules.checkAddRentalRequest(addRentalRequest) //businessRule ile check ederken sıkıntı çıkmazsa requesti geri dönüyoruz.
                .setStartKilometer(carService.getById(addRentalRequest.getCarId()).getKilometer());

        RentalEntity rentalEntity = modelMapperService.forRequest().map(addRentalRequest, RentalEntity.class);

        PaymentDetailsEntity paymentDetailsEntity = rentalEntity.getPaymentDetailsEntity();

        paymentDetailsEntity.setAmount(this.rentalBusinessRules
                .calculateTotalBasePrice(rentalBusinessRules.calculateTotalRentalDays(rentalEntity.getStartDate(), rentalEntity.getEndDate())
                        , rentalEntity.getCarEntity().getRentalPrice()));

        paymentDetailsEntity.setPaymentTypeEntity(this.paymentTypeEntityRepository
                .findById(addRentalRequest.getPaymentTypeId()).orElseThrow());

        rentalEntity.setPaymentDetailsEntity(paymentDetailsEntity);

        rentalEntity.setItemType(RENTAL);

        return this.modelMapperService.forResponse().map(rentalEntityService.add(rentalEntity), RentalDTO.class);
    }

    @Override
    public RentalDTO update(UpdateRentalRequest updateRentalRequest) {
        //todo: ceza işlemleri , indirim işlemleri iptali kontrol edilecek sonuçta da totalPrice güncelleme

        RentalEntity rentalEntity = this.rentalEntityService.update(this.modelMapperService
                .forRequest().map(updateRentalRequest, RentalEntity.class));

        PaymentDetailsEntity paymentDetailsEntity = rentalEntity.getPaymentDetailsEntity();

        paymentDetailsEntity.setAmount(this.rentalBusinessRules
                .calculateTotalFinalAmount(updateRentalRequest
                        , rentalBusinessRules.calculateTotalRentalDays(rentalEntity.getStartDate(), rentalEntity.getEndDate())));

        rentalEntity.setPaymentDetailsEntity(paymentDetailsEntity);

        rentalEntity.setItemType(RENTAL);

        this.rentalEntityService.update(rentalEntity);

        return this.modelMapperService.forResponse().map(rentalEntity, RentalDTO.class);
    }

    @Override
    public RentalDTO getById(int id) {
        return this.modelMapperService.forResponse().map(rentalEntityService.getById(id), RentalDTO.class);
    }

    @Override
    public void delete(int id) {
        this.rentalEntityService.delete(this.rentalEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {

    }

    @Override
    public List<RentalDTO> getAll() {
        return this.rentalEntityService.getAll().stream()
                .map(rentalEntity -> modelMapperService.forResponse().map(rentalEntity, RentalDTO.class)).toList();
    }

    @Override
    public List<RentalDTO> getAllByIsDeletedFalse() {
        return null;
    }

    @Override
    public List<RentalDTO> getAllByIsDeletedTrue() {
        return null;
    }
}
