package source_files.data.requests.itemRequests.paymentRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayWithCreditCardRequest {
    int paymentDetailsId;
    CreditCardInformation creditCardInformation;

}
