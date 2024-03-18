package src.controller.payment.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class PaymentDetailsResponse {
    int id;
    int paymentTypeEntityId;
    double amount;
    String PaymentTypeEntityName;
    LocalDateTime createdDate;
    boolean isDeleted;

    @Override
    public String toString() {
        return "PaymentDetailsResponse{" +
                "id=" + id +
                ", paymentTypeEntityId=" + paymentTypeEntityId +
                ", amount=" + amount +
                ", PaymentTypeEntityName='" + PaymentTypeEntityName + '\'' +
                ", createdDate=" + createdDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
