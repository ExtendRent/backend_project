package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountCodeEntity;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.dataAccess.paperWorkRepositories.DiscountCodeRepository;
import source_files.dataAccess.paperWorkRepositories.RentalRepository;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.dataAccess.vehicleRepositories.CarRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RentalBusinessRules {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    private final DiscountCodeRepository discountCodeRepository;


    public void checkRentalRequest(AddRentalRequest addRentalRequest) {
        this.checkStartDate(addRentalRequest.getStartDate());
        this.checkEndDate(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());
        this.carExists(addRentalRequest.getCarId());
        this.userExists(addRentalRequest.getCustomerId());
        this.checkTotalRentalDays(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());
        this.checkDiscountCode(addRentalRequest.getDiscountCodeId());
    }


    //---------------CHECKING METHODS--------------------------------

    private void checkStartDate(LocalDate startDate) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isBefore(startDate)){
            throw new IllegalStateException("Başlangıç tarihi bugünün tarihinden küçük olamaz.");
        }
    }

    private void checkEndDate(LocalDate startDate ,LocalDate endDate) {
        if (endDate.isBefore(startDate)){
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
        if (daysBetween > 25){
            throw new IllegalStateException("Kiralama tarihi maksimum 25 gün olabilir. Lütfen tarih aralığınızı buna göre düzenleyiniz.");
        }
    }

    private void checkDiscountCode(int id) {
        if ( ! this.discountCodeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("böyle bir indirim kodumuz bulunmamaktadır"))
                .isActive() ) //ifin içerisi = indirim kodunun olup olmadığına, varsa ise aktifmi değilmi diye bakıyor.
        {
            throw new IllegalStateException("Bu indirim kodu artık geçersizdir.");
        }
    }

    //-----------------------------------------------------------------

    private double calculateTotalRentalDays(LocalDate startDate, LocalDate endDate,LocalDate returnDate, double rentalPrice,int id) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        this.discountCodeRepository.findById(id);
        if (endDate.isEqual(returnDate)){
            double amount = daysBetween * rentalPrice ;
        }

        return 0;
    }

}
