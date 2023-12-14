package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.Item;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "payment_type")
public class PaymentTypeEntity extends Item {
    @Column(name = "name")
    private String name;


}
