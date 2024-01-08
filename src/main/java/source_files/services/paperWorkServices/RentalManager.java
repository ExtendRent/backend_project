package source_files.services.paperWorkServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.UpdateRentalRequest;
import source_files.services.BusinessRules.RentalBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;
import source_files.services.paperWorkServices.abstracts.RentalService;
import source_files.services.systemServices.SysPaymentDetailsService;
import source_files.services.vehicleService.abstracts.CarService;

import java.util.List;

import static source_files.data.types.ItemType.RENTAL;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private final RentalEntityService rentalEntityService;
    private final ModelMapperService modelMapperService;
    private final CarService carService;
    private final SysPaymentDetailsService sysPaymentDetailsService;
    private RentalBusinessRules rentalBusinessRules;


    @Override
    public RentalDTO add(AddRentalRequest addRentalRequest) {
        // indirim işlemleri sonucu totalPrice hesaplama
        //TODO ekleme işleminde null gelme problemleri var.
        RentalEntity rentalEntity = modelMapperService.forRequest()
                .map(rentalBusinessRules.checkAddRentalRequest(
                        rentalBusinessRules.fixAddRentalRequest(addRentalRequest)), RentalEntity.class);

        rentalEntity.setStartKilometer(carService.getById(addRentalRequest.getCarEntityId()).getKilometer());

        rentalEntity.setPaymentDetailsEntity(this.sysPaymentDetailsService
                .add(this.rentalBusinessRules.createAddPaymentDetailsRequest(addRentalRequest)));

        rentalEntity.setItemType(RENTAL);

        return this.modelMapperService.forResponse().map(rentalEntityService.add(rentalEntity), RentalDTO.class);
    }

    @Override
    public RentalDTO returnCar(ReturnRentalRequest returnRentalRequest) {
        // ceza işlemleri , indirim işlemleri iptali kontrol edilecek sonuçta da totalPrice güncelleme
        //TODO update işleminde paymentDetail bulunamıyor

        RentalEntity rentalEntity = this.modelMapperService.forRequest().map(returnRentalRequest, RentalEntity.class);

        rentalEntity.setPaymentDetailsEntity(this.sysPaymentDetailsService.update(
                this.rentalBusinessRules.createUpdatePaymentDetailsRequest(returnRentalRequest)));

        rentalEntity.setItemType(RENTAL);

        return this.modelMapperService.forResponse().map(this.rentalEntityService.update(rentalEntity), RentalDTO.class);
    }

    @Override
    public RentalDTO update(UpdateRentalRequest updateRentalRequest) {
        return null;
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
