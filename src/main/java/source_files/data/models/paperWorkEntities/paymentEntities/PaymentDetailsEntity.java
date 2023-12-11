package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.Item;
import source_files.data.types.PaymentType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "payment_details")
public class PaymentDetailsEntity extends Item {

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "amount")
    private int amount;

}
