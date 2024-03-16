package src.data.models.paperwork_entities.paymentEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controllers.paperwork.responses.PaymentDetailsResponse;
import src.data.models.base_entities.BaseEntity;
import src.data.models.paperwork_entities.rentalEntities.RentalEntity;

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
