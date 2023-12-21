package source_files.data.requests.itemRequests.RentalRequests;

import lombok.Getter;
import lombok.Setter;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.requests.BaseRequest;

import java.time.LocalDate;


@Getter
@Setter
public class AddRentalRequest extends BaseRequest {

    int customerId;

    int carId;

    LocalDate startDate;

    LocalDate endDate;

    int discountCodeId;

    int paymentTypeId;
    //totalPrice hesaplanarak kayıt edilmelidir (kullanıcı vermeyecek)
    CreditCardInformation creditCardInformation;

    Integer startKilometer;

}
