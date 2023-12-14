package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.Item;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "payment_details")
public class PaymentDetailsEntity extends Item {

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private PaymentTypeEntity paymentTypeEntity;
}
