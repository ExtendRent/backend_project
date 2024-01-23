package source_files.services.paperWorkServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.DTO.paperWorkDTOs.ShowRentalResponse;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.requests.paperworkRequests.RentalRequests.CreateRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ShowRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.UpdateRentalRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.services.BusinessRules.paperWork.RentalBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountEntityService;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;
import source_files.services.paperWorkServices.abstracts.RentalService;
import source_files.services.systemServices.SysPaymentDetailsService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.vehicleService.abstracts.CarService;

import java.util.List;

import static source_files.data.types.itemTypes.ItemType.RENTAL;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private final RentalEntityService rentalEntityService;
    private final ModelMapperService modelMapperService;
    private final CarService carService;
    private final SysPaymentDetailsService sysPaymentDetailsService;
    private final CustomerService customerService;

    PaymentTypeEntityService paymentTypeEntityService;
    DiscountEntityService discountEntityService;
    private RentalBusinessRules rentalBusinessRules;

    @Override
    public ShowRentalResponse showRentalDetails(ShowRentalRequest showRentalRequest) {

        return this.convertToShowRentalResponse(showRentalRequest);
    }

    @Override
    public void create(CreateRentalRequest createRentalRequest) {
        // indirim işlemleri sonucu totalPrice hesaplama

        RentalEntity rentalEntity = modelMapperService.forRequest()
                .map(rentalBusinessRules.checkCreateRentalRequest(
                                rentalBusinessRules.checkCreateRentalRequest(createRentalRequest))
                        , RentalEntity.class);

        rentalEntity.setPaymentDetailsEntity(
                this.sysPaymentDetailsService.getById(createRentalRequest.getPaymentDetailsDTO().getId())
        );
        rentalEntity.setDiscountEntity(this.discountEntityService
                .getByDiscountCode(createRentalRequest.getDiscountCode())
        );
        rentalEntity.setStartKilometer(carService.getById(createRentalRequest.getCarEntityId()).getKilometer());
        rentalEntity.setItemType(RENTAL);

        UpdateCarRequest updateCarRequest = this.modelMapperService.forResponse().map(
                this.carService.getById(createRentalRequest.getCarEntityId()), UpdateCarRequest.class
        );
        updateCarRequest.setAvailable(false);
        updateCarRequest.setAvailabilityDate(rentalEntity.getEndDate());
        rentalEntityService.create(rentalEntity);
        carService.update(updateCarRequest);
    }

    @Override
    public RentalDTO returnCar(ReturnRentalRequest returnRentalRequest) {
        // ceza işlemleri , indirim işlemleri iptali kontrol edilecek sonuçta da totalPrice güncelleme

        RentalEntity rentalEntity = this.rentalEntityService.getById(returnRentalRequest.getRentalEntityId());

        rentalEntity.setPaymentDetailsEntity(this.sysPaymentDetailsService.update(
                this.rentalBusinessRules.createUpdatePaymentDetailsRequest(returnRentalRequest)));

        rentalEntity.setActive(false);
        rentalEntity.getCarEntity().setKilometer(rentalEntity.getEndKilometer());
        this.carService.update(this.modelMapperService.forResponse().map(rentalEntity.getCarEntity(), UpdateCarRequest.class));
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
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.rentalEntityService.delete(this.rentalEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        RentalEntity rentalEntity = this.rentalEntityService.getById(id);
        rentalEntity.setIsDeleted(true);
        rentalEntityService.update(rentalEntity);
    }

    @Override
    public List<RentalDTO> getAll() {
        return this.rentalEntityService.getAll().stream()
                .map(rentalEntity -> modelMapperService.forResponse().map(rentalEntity, RentalDTO.class)).toList();
    }

    @Override
    public List<RentalDTO> getAllByDeletedState(boolean isDeleted) {
        return this.rentalEntityService.getAllByDeletedState(isDeleted).stream()
                .map(rentalEntity -> modelMapperService.forResponse()
                        .map(rentalEntity, RentalDTO.class)).toList();
    }


    public ShowRentalResponse convertToShowRentalResponse(ShowRentalRequest showRentalRequest) {

        ShowRentalResponse showRentalResponse = this.modelMapperService.forResponse()
                .map(this.rentalBusinessRules.checkShowRentalRequest(showRentalRequest), ShowRentalResponse.class
                );

        showRentalResponse.setCarDTO(carService.getById(showRentalRequest.getCarEntityId()));
        showRentalResponse.setCustomerDTO(customerService.getById(showRentalRequest.getCustomerEntityId()));
        showRentalResponse.setAmount(this.rentalBusinessRules.calculateAmount(showRentalRequest));
        return showRentalResponse;
    }
}
