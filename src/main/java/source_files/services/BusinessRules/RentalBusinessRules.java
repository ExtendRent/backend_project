package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.paperWorkDTOs.DiscountCodeDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.itemRequests.paymentRequests.AddPaymentDetailsRequest;
import source_files.data.requests.itemRequests.paymentRequests.UpdatePaymentDetailsRequest;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.exception.DataNotFoundException;
import source_files.exception.ValidationException;
import source_files.services.entityServices.paperWorkEntityManagers.RentalEntityManager;
import source_files.services.paperWorkServices.abstracts.DiscountCodeService;
import source_files.services.vehicleService.abstracts.CarService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.RENTAL_LIST_NOT_FOUND;
import static source_files.exception.exceptionTypes.ValidationExceptionType.VALIDATION_EXCEPTION;

@AllArgsConstructor
@Service
public class RentalBusinessRules implements BaseBusinessRulesService {

    private final RentalEntityManager rentalEntityManager;
    private final CustomerRepository customerRepository;

    private final DiscountCodeService discountCodeService;
    private final CarService carService;
    private PaymentBusinessRules paymentBusinessRules;


    //--------------------- AUTO FIX METHODS ---------------------
    public AddRentalRequest fixAddRentalRequest(AddRentalRequest addRentalRequest) {
        if (addRentalRequest.getDiscountCode() != null) {
            addRentalRequest.setDiscountCode(this.fixName(addRentalRequest.getDiscountCode()));
        }
        addRentalRequest.setDiscountCode(this.fixName(addRentalRequest.getDiscountCode()));
        return addRentalRequest;
    }

    public ReturnRentalRequest fixReturnRentalRequest(ReturnRentalRequest returnRentalRequest) {

        return returnRentalRequest;
    }

    //---------------AUTO CHECKING METHODS--------------------------------
    public AddRentalRequest checkAddRentalRequest(AddRentalRequest addRentalRequest) {

        this.checkEndDate(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());
        this.carExists(addRentalRequest.getCarEntityId());
        this.userExists(addRentalRequest.getCustomerEntityId());
        this.checkTotalRentalDays(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());
        if (addRentalRequest.getDiscountCode() != null) {
            this.checkDiscountCode(discountCodeService.getByDiscountCode(addRentalRequest.getDiscountCode()));
        }

        return addRentalRequest;
    }

    public ReturnRentalRequest checkReturnRentalRequest(ReturnRentalRequest returnRentalRequest) {

        return returnRentalRequest;
    }


    //----------------------------METHODS--------------------------------

    public AddPaymentDetailsRequest createAddPaymentDetailsRequest(AddRentalRequest addRentalRequest) {
        AddPaymentDetailsRequest addPaymentDetailsRequest = new AddPaymentDetailsRequest();

        addPaymentDetailsRequest.setAmount(this.calculateTotalAmount(
                this.calculateTotalBasePrice(
                        this.calculateTotalRentalDays(addRentalRequest.getStartDate(), addRentalRequest.getEndDate())
                        , this.carService.getById(addRentalRequest.getCarEntityId()).getRentalPrice())
                , this.discountCodeService.getByDiscountCode(
                        addRentalRequest.getDiscountCode()).getDiscountPercentage()));

        addPaymentDetailsRequest.setPaymentTypeEntityId(addRentalRequest.getPaymentTypeEntityId());

        return addPaymentDetailsRequest;
    }

    public UpdatePaymentDetailsRequest createUpdatePaymentDetailsRequest(ReturnRentalRequest returnRentalRequest) {
        RentalEntity rentalEntity = this.rentalEntityManager.getById(returnRentalRequest.getRentalEntityId());
        UpdatePaymentDetailsRequest updatePaymentDetailsRequest = new UpdatePaymentDetailsRequest();

        PaymentDetailsEntity paymentDetailsEntity = rentalEntity.getPaymentDetailsEntity();

        updatePaymentDetailsRequest.setPaymentDetailsId(paymentDetailsEntity.getId());
        updatePaymentDetailsRequest.setPaymentType(paymentDetailsEntity.getPaymentTypeEntity().getPaymentType());
        //TODO  calculate total final amount kısmında hata var. cezalı hesaplamayı yanlış yapıyor.
        updatePaymentDetailsRequest.setAmount(
                this.calculateTotalFinalAmount(returnRentalRequest
                        , this.calculateTotalRentalDays(rentalEntity.getStartDate(), rentalEntity.getEndDate())));


        return updatePaymentDetailsRequest;
    }

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(RENTAL_LIST_NOT_FOUND, "Aradığınız kriterlere uygun kiralama kaydı bulunamadı");
        }
        return list;
    }

    @Override
    public String fixName(String name) {
        return name.replace(" ", "").toUpperCase();
    }


    private void checkEndDate(LocalDate startDate, LocalDate endDate) {

        if (endDate.isBefore(startDate)) {
            throw new ValidationException(
                    VALIDATION_EXCEPTION, "Bitiş tarihi başlangıç tarihinden küçük olamaz.");
        }
    }

    private void carExists(int carId) {
        this.carService.getById(carId);
    }

    private void userExists(int customerId) {
        this.customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Bu kullanıcı sistemde bulunamadı"));
    }

    private void checkTotalRentalDays(LocalDate startDate, LocalDate endDate) {

        if (this.calculateTotalRentalDays(startDate, endDate) > 25) {
            throw new ValidationException(VALIDATION_EXCEPTION, "Kiralama tarihi maksimum 25 gün olabilir. Lütfen tarih aralığınızı buna göre düzenleyiniz.");
        }
    }

    private void checkDiscountCode(DiscountCodeDTO discountCodeDTO) {

        if (!discountCodeDTO.isActive()) // varsa aktifmi değilmi diye bakıyor.
        {
            throw new ValidationException(VALIDATION_EXCEPTION, "Bu indirim kodu artık geçersizdir.");
        }
    }


    //----------------------------CALCULATING METHODS-------------------------------------


    public int calculateTotalRentalDays(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    private int calculateDelayDay(LocalDate endDate, LocalDate returnDate) {
        return Math.toIntExact(ChronoUnit.DAYS.between(endDate, returnDate));
    }

    public double calculateTotalBasePrice(int totalRentalDays, double rentalPrice) {
        return totalRentalDays * rentalPrice;
    }

    public double calculateTotalAmount(double baseTotalPrice, Integer discountPercent) {

        if (discountPercent == null) {//discount code girilmezse percent null gelecektir.
            return baseTotalPrice;
        }
        return this.calculateTotalPriceWithDiscount(discountPercent, baseTotalPrice);
    }

    private double calculateTotalPriceWithDiscount(Integer discountPercent, double baseTotalPrice) {
        return baseTotalPrice - (baseTotalPrice * discountPercent / 100);
    }

    public double calculateTotalFinalAmount(ReturnRentalRequest returnRentalRequest, int totalRentalDays) {
        RentalEntity rentalEntity = this.rentalEntityManager
                .getById(returnRentalRequest.getRentalEntityId());

        double baseTotalPrice = calculateTotalBasePrice(totalRentalDays, rentalEntity.getCarEntity().getRentalPrice());

        if (!(returnRentalRequest.isActive()) && returnRentalRequest.getReturnDate().isBefore(rentalEntity.getEndDate())) {//İlk if - zamanından erken getirirse indirim iptali.
            return baseTotalPrice;

        } else if (!(returnRentalRequest.isActive()) && returnRentalRequest.getReturnDate().isAfter(rentalEntity.getEndDate())) { ////İkinci if - zamanından sonra teslim edildiyse.
            return baseTotalPrice + (calculateDelayDay(rentalEntity.getEndDate(), returnRentalRequest.getReturnDate()) * 100); //Gün başına 100 tl ceza ve indirim iptal.
        }
        return rentalEntity.getPaymentDetailsEntity().getAmount(); //zamanında getirirse
    }


}
