package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountCodeEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.UpdateRentalRequest;
import source_files.dataAccess.paperWorkRepositories.DiscountCodeRepository;
import source_files.dataAccess.paperWorkRepositories.RentalRepository;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.exception.DataNotFoundException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.DISCOUNT_CODE_NOT_FOUND;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.RENTAL_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class RentalBusinessRules implements BaseBusinessRulesService {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final DiscountCodeRepository discountCodeRepository;
    private PaymentBusinessRules paymentBusinessRules;


    //--------------------- AUTO FIX METHODS ---------------------


    //---------------AUTO CHECKING METHODS--------------------------------
    public AddRentalRequest checkAddRentalRequest(AddRentalRequest addRentalRequest) {
        this.checkStartDate(addRentalRequest.getStartDate());
        this.checkEndDate(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());
        this.carExists(addRentalRequest.getCarId());
        this.userExists(addRentalRequest.getCustomerId());
        this.checkTotalRentalDays(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());
        this.checkDiscountCode(addRentalRequest.getDiscountCodeId());
        this.checkCreditCardInformations(addRentalRequest.getCreditCardInformation());
        return addRentalRequest;
    }


    //---------------AUTO CHECKING METHODS--------------------------------

    public AddRentalRequest fixAddRentalRequest(AddRentalRequest addRentalRequest) {
        addRentalRequest.setCreditCardInformation(this.paymentBusinessRules
                .fixCreditCardInformation(addRentalRequest.getCreditCardInformation()));

        return addRentalRequest;
    }

    private void checkStartDate(LocalDate startDate) {

        if (startDate.isAfter(LocalDate.now())) {
            throw new IllegalStateException("Başlangıç tarihi bugünün tarihinden küçük olamaz.");
        }
    }

    private void checkEndDate(LocalDate startDate, LocalDate endDate) {

        if (endDate.isBefore(startDate)) {
            throw new IllegalStateException("Bitiş tarihi başlangıç tarihinden küçük olamaz.");
        }
    }

    private void carExists(int carId) {
        this.carRepository.findById(carId)
                .orElseThrow(() -> new IllegalStateException("Bu araç sistemde bulunamadı"));
    }

    private void userExists(int customerId) {
        this.customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Bu kullanıcı sistemde bulunamadı"));
    }

    private void checkTotalRentalDays(LocalDate startDate, LocalDate endDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        if (daysBetween > 25) {
            throw new IllegalStateException("Kiralama tarihi maksimum 25 gün olabilir. Lütfen tarih aralığınızı buna göre düzenleyiniz.");
        }
    }

    private void checkDiscountCode(Integer id) {
        if (id != null) {
            if (!this.discountCodeRepository.findById(id)
                    .orElseThrow(() -> new DataNotFoundException(DISCOUNT_CODE_NOT_FOUND, "böyle bir indirim kodumuz bulunmamaktadır."))
                    .isActive()) //ifin içerisi = indirim kodunun olup olmadığına, varsa ise aktifmi değilmi diye bakıyor.
            {
                throw new IllegalStateException("Bu indirim kodu artık geçersizdir.");
            }
        }

    }

    private void checkCreditCardInformations(CreditCardInformation creditCardInformation) {
        this.paymentBusinessRules.checkCreditCard(creditCardInformation);
    }

    //-----------------------------------------------------------------

    public int calculateTotalRentalDays(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    public int calculateDelayDay(LocalDate endDate, LocalDate returnDate) {
        return Math.toIntExact(ChronoUnit.DAYS.between(endDate, returnDate));
    }

    public double calculateTotalBasePrice(int totalRentalDays, double rentalPrice) {
        return totalRentalDays * rentalPrice;
    }

    public double calculateTotalAmount(double baseTotalPrice, int discountCodeId) {

        double totalPrice = baseTotalPrice;

        if (this.discountCodeRepository.findById(discountCodeId).isPresent()) {
            totalPrice = this.calculateTotalPriceWithDiscount(discountCodeId, baseTotalPrice);
        }

        return totalPrice;
    }

    public double calculateTotalPriceWithDiscount(int discountCodeId, double totalPrice) {

        if (this.discountCodeRepository.findById(discountCodeId).isPresent()) {

            DiscountCodeEntity discountCode = this.discountCodeRepository
                    .findById(discountCodeId).orElseThrow();

            totalPrice = totalPrice - (totalPrice * discountCode.getDiscountPercentage() / 100);
        }
        return totalPrice;
    }

    public double calculateTotalFinalAmount(UpdateRentalRequest updateRentalRequest, int totalRentalDays) {
        RentalEntity rentalEntity = this.rentalRepository
                .findById(updateRentalRequest.getRentalEntityId()).orElseThrow();

        double baseTotalPrice = calculateTotalBasePrice(totalRentalDays, rentalEntity.getCarEntity().getRentalPrice());
        double totalAmount = calculateTotalAmount(baseTotalPrice, rentalEntity.getDiscountCode().getId());

        if (!(updateRentalRequest.isActive()) && updateRentalRequest.getReturnDate().isBefore(rentalEntity.getEndDate())) {//İlk if - zamanından erken getirirse indirim iptali.
            return baseTotalPrice;

        } else if (!(updateRentalRequest.isActive()) && updateRentalRequest.getReturnDate().isAfter(rentalEntity.getEndDate())) { ////İkinci if - zamanından sonra teslim edildiyse.
            return baseTotalPrice + (calculateDelayDay(rentalEntity.getEndDate(), updateRentalRequest.getReturnDate()) * 100); //Gün başına 100 tl ceza ve indirim iptal.

        }
        return totalAmount; //zamanında getirirse
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
        return null;
    }
}
