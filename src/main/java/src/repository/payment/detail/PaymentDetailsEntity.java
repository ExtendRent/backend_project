package src.repository.payment.detail;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.payment.detail.response.PaymentDetailsResponse;
import src.core.BaseEntity;
import src.repository.payment.type.PaymentTypeEntity;
import src.repository.rental.RentalEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder(builderMethodName = "paymentDetailsBuilder")
@Table(name = "payment_details")
public class PaymentDetailsEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private RentalEntity rentalEntity;
    @Column(name = "amount")
    private double amount;
    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private PaymentTypeEntity paymentTypeEntity;

    public PaymentDetailsEntity(double amount, PaymentTypeEntity paymentTypeEntity) {
        this.amount = amount;
        this.paymentTypeEntity = paymentTypeEntity;
    }

    public PaymentDetailsResponse toModel() {
        return PaymentDetailsResponse.builder()
                .id(getId())
                .amount(getAmount())
                .paymentTypeEntityId(getPaymentTypeEntity().getId())
                .PaymentTypeEntityName(getPaymentTypeEntity().getName())
                .createdDate(getCreatedDate())
                .isDeleted(getIsDeleted())
                .build();
    }
}
