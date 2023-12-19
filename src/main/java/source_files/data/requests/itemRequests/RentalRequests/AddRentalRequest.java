package source_files.data.requests.itemRequests.RentalRequests;

import lombok.Getter;
import lombok.Setter;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountCodeEntity;
import source_files.data.types.PaymentType;

import java.time.LocalDate;


@Getter
@Setter
public class AddRentalRequest {

    int customerId;

    int carId;

    LocalDate startDate;

    LocalDate endDate;

    int discountCodeId;

    PaymentType paymentType;
    //totalPrice hesaplanarak kayıt edilmelidir (kullanıcı vermeyecek)
    CreditCardInformation creditCardInformation;

    Integer startKilometer;

}
