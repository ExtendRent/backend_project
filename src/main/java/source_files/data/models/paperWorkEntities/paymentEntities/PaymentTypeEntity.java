package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.Item;
import source_files.data.types.PaymentType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
@Entity
@Table(name = "payment_type")
public class PaymentTypeEntity extends Item {
    @Column(name = "paymentType")
    private PaymentType paymentType;
}
