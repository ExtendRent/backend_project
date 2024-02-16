package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.enums.defaultDataEnums.DefaultPaymentType;
import source_files.data.models.baseEntities.Item;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_type")
public class PaymentTypeEntity extends Item {

    @Column(name = "name")
    private String name;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private DefaultPaymentType paymentType;

    @Column(name = "is_active")
    private boolean isActive = true;
}
