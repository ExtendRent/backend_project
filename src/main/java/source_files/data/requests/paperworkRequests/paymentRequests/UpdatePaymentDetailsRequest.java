package source_files.data.requests.paperworkRequests.paymentRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.requests.BaseRequest;
import source_files.data.types.PaymentType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentDetailsRequest implements BaseRequest {
    private int paymentDetailsId;
    private double amount;
    private PaymentType paymentType;
}
