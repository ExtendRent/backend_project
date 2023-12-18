package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.dataAccess.paperWorkRepositories.DiscountCodeRepository;
import source_files.dataAccess.paperWorkRepositories.RentalRepository;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.dataAccess.vehicleRepositories.CarRepository;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class RentalBusinessRules {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    private final DiscountCodeRepository discountCodeRepository;


    public void checkRentalRequest(AddRentalRequest addRentalRequest) {
        this.checkStartDate(addRentalRequest.getStartDate());
        this.checkEndDate(addRentalRequest.getEndDate());
        this.carExists(addRentalRequest.getCarId());
        this.userExists(addRentalRequest.getCustomerId());
        this.checkTotalRentalDays(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());
        this.checkDiscountCode(addRentalRequest.getDiscountCode());
    }


    //---------------CHECKING METHODS--------------------------------

    private void checkStartDate(LocalDate startDate) {

    }

    private void checkEndDate(LocalDate endDate) {

    }

    private void carExists(int carId) {
        this.carRepository.findById(carId)
                .orElseThrow(() -> new IllegalStateException("araç bulunamadı"));
    }

    private void userExists(int customerId) {
        this.customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Kullanıcı bulunamadı"));
    }

    private void checkTotalRentalDays(LocalDate startDate, LocalDate endDate) {

    }

    private void checkDiscountCode(String discountCode) {
        if ( ! this.discountCodeRepository.findByCode(discountCode)
                .orElseThrow(() -> new IllegalStateException("böyle bir indirim kodumuz bulunmamaktadır"))
                .isActive() ) //ifin içerisi = indirim kodunun olup olmadığına, varsa ise aktifmi değilmi diye bakıyor.
        {
            throw new IllegalStateException("Bu indirim kodu artık geçersizdir.");
        }
    }

    //-----------------------------------------------------------------

    private int calculateTotalRentalDays(LocalDate startDate, LocalDate endDate) {
        return 0;
    }

}
