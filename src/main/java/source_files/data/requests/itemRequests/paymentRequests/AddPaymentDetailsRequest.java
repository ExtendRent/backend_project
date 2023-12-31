package source_files.data.requests.itemRequests.paymentRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPaymentDetailsRequest implements BaseRequest {

    private double amount;
    private int paymentTypeEntityId;
}
