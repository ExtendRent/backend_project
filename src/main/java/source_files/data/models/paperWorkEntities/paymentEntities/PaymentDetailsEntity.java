package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.Item;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@SuperBuilder
@Table(name = "payment_details")
public class PaymentDetailsEntity extends Item {

    @Column(name = "amount")
    @Pattern(regexp = "^[0-9]+$", message = "Fiyat sadece sayılardan oluşmalıdır.")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private PaymentTypeEntity paymentTypeEntity;

}
