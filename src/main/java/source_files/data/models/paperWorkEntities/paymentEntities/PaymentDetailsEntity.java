package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.Item;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_details")
public class PaymentDetailsEntity extends Item {
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
}
