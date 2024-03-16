package src.services.paperwork.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.paperwork.requests.Rental.CreateRentalRequest;
import src.controllers.paperwork.requests.Rental.ReturnRentalRequest;
import src.controllers.paperwork.requests.Rental.ShowRentalRequest;
import src.core.exception.DataNotFoundException;
import src.core.exception.NotSuitableException;
import src.core.exception.ValidationException;
import src.data.models.paperwork_entities.paymentEntities.PaymentDetailsEntity;
import src.data.models.paperwork_entities.rentalEntities.RentalEntity;
import src.services.businessrules.abstracts.BaseRules;
import src.services.paperwork.discount.DiscountService;
import src.services.user.customer.CustomerService;
import src.services.vehicle.car.CarRules;
import src.services.vehicle.car.CarService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.RENTAL_LIST_NOT_FOUND;
import static src.core.exception.exception_types.NotSuitableExceptionType.DRIVING_LICENSE_TYPE_NOT_SUITABLE;
import static src.core.exception.exception_types.ValidationExceptionType.VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@Service
public class RentalRules implements BaseRules {

    private final RentalEntityServiceImpl rentalEntityServiceImpl;
    private final DiscountService discountService;
    private final CarService carService;
    private final CustomerService customerService;
    private final CarRules carRules;

    //--------------------- AUTO FIX METHODS ---------------------
    public CreateRentalRequest fix(CreateRentalRequest createRentalRequest) {

        if (this.discountCodeIsNotNull(createRentalRequest.getDiscountCode())) {
            createRentalRequest.setDiscountCode(this.fixName(createRentalRequest.getDiscountCode()));
        }
        return createRentalRequest;
    }

    public ReturnRentalRequest fix(ReturnRentalRequest returnRentalRequest) {

        return returnRentalRequest;
    }

    //---------------AUTO CHECKING METHODS--------------------------------

    public void check(ShowRentalRequest showRentalRequest) {
        this.checkDrivingLicenseType(showRentalRequest.getCarEntityId(), showRentalRequest.getCustomerEntityId());
        this.carExists(showRentalRequest.getCarEntityId());
        this.checkDiscountCode(showRentalRequest.getDiscountCode());
    }

    public void check(CreateRentalRequest createRentalRequest) {
        this.userExists(createRentalRequest.getCustomerEntityId());
    }

    public void check(ReturnRentalRequest returnRentalRequest) {
    }


    //----------------------------METHODS--------------------------------


    public PaymentDetailsEntity updatePaymentDetailsToFinal(ReturnRentalRequest returnRentalRequest) {
        RentalEntity rentalEntity = this.rentalEntityServiceImpl.getById(returnRentalRequest.getId());

        PaymentDetailsEntity paymentDetailsEntity = rentalEntity.getPaymentDetailsEntity();

        //TODO  calculate total final amount kısmında hata var. cezalı hesaplamayı yanlış yapıyor.

        paymentDetailsEntity.setAmount(
                this.calculateReturnFinalAmount(returnRentalRequest
                        , this.calculateTotalRentalDays(rentalEntity.getStartDate(), rentalEntity.getEndDate())));

        return paymentDetailsEntity;
    }

    public void checkDrivingLicenseType(int carId, Integer customerId) {
        //Giriş yapmadan araç listeleyebilmek için customerId null verilebilmelidir.
        //CustomerId verilmiş ise ve beklenen ehliyet tipine uyuyorsa:
        if (!carRules.isDrivingLicenseTypeSuitable(carId, customerId)) {
            throw new NotSuitableException(DRIVING_LICENSE_TYPE_NOT_SUITABLE);
        }
    }

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(RENTAL_LIST_NOT_FOUND);
        }

    }

    @Override
    public String fixName(String name) {
        return name.replace(" ", "").toUpperCase();
    }


    private void carExists(int carId) {
        this.carService.getById(carId);
    }

    private void userExists(int customerId) {
        this.customerService.getById(customerId);
    }

    public void checkDiscountCode(String discountCode) {
        if (this.discountCodeIsNotNull(discountCode)) { //discountCode girilmiş mi
            if (!this.discountService.getByDiscountCode(discountCode).isActive()) // varsa aktifmi değilmi diye bakıyor.
            {
                throw new ValidationException(VALIDATION_EXCEPTION);
            }
        }
    }

    public boolean discountCodeIsNotNull(String discountCode) {
        return discountCode != null && !discountCode.equals("");
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


    private double calculateTotalPriceWithDiscount(double baseTotalPrice, int discountPercent) {
        // Discount yüzdesini ondalık formata çevir
        double discountPercentage = (double) discountPercent / 100.0;

        // İndirimi uygula
        return baseTotalPrice - (baseTotalPrice * discountPercentage);
    }

    public double calculateReturnFinalAmount(ReturnRentalRequest returnRentalRequest, int totalRentalDays) {
        RentalEntity rentalEntity = this.rentalEntityServiceImpl
                .getById(returnRentalRequest.getId());

        double baseTotalPrice = calculateTotalBasePrice(totalRentalDays, rentalEntity.getCarEntity().getRentalPrice());

        if (returnRentalRequest.getReturnDate().isBefore(rentalEntity.getEndDate())) {//İlk if - zamanından erken getirirse indirim iptali.
            return baseTotalPrice;

        } else if (returnRentalRequest.getReturnDate().isAfter(rentalEntity.getEndDate())) { ////İkinci if - zamanından sonra teslim edildiyse.
            return baseTotalPrice + (calculateDelayDay(rentalEntity.getEndDate(), returnRentalRequest.getReturnDate()) * 100); //Gün başına 100 tl ceza ve indirim iptal.
        }

        return rentalEntity.getPaymentDetailsEntity().getAmount(); //zamanında getirirse
    }

    public double calculateAmount(ShowRentalRequest showRentalRequest) {
        double totalBasePrice = this.calculateTotalBasePrice(
                this.calculateTotalRentalDays(showRentalRequest.getStartDate(), showRentalRequest.getEndDate())
                , this.carService.getById(showRentalRequest.getCarEntityId()).getRentalPrice()
        );

        if (this.discountCodeIsNotNull(showRentalRequest.getDiscountCode())) {// Discount kodu doluysa
            return this.calculateTotalPriceWithDiscount(
                    totalBasePrice,
                    this.discountService.getByDiscountCode(
                            showRentalRequest.getDiscountCode()).getDiscountPercentage()
            );
        } else {
            // Discount kodu yoksa, direkt kira ücretini kullan
            return totalBasePrice;
        }
    }
}
