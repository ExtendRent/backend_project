package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "discount_codes")
public class DiscountCodeEntity extends Item {
    @Column(name = "discount_code")
    private String discountCode;
    @Column(name = "discount_percentage")
    private int discountPercentage;
    @Column(name = "is_active")
    private boolean isActive;

}
