package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.controllers.paperWork.dtos.PaymentDetailsDTO;
import source_files.data.models.baseEntities.BaseEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;

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

    public PaymentDetailsDTO toModel() {
        return PaymentDetailsDTO.builder()
                .id(getId())
                .amount(getAmount())
                .paymentTypeEntityId(getPaymentTypeEntity().getId())
                .PaymentTypeEntityName(getPaymentTypeEntity().getName())
                .createdDate(getCreatedDate())
                .isDeleted(getIsDeleted())
                .build();
    }
}
