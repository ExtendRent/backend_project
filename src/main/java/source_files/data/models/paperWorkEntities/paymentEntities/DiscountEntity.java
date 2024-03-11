package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.paperWorkDTOs.DiscountDTO;
import source_files.data.models.baseEntities.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder(builderMethodName = "discountBuilder")
@Table(name = "discount_codes")
public class DiscountEntity extends BaseEntity {
    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "discount_percentage")
    private int discountPercentage;

    @Column(name = "is_active")
    private boolean isActive = true;

    public DiscountDTO toModel() {
        return DiscountDTO.builder()
                .id(getId())
                .discountCode(getDiscountCode())
                .discountPercentage(getDiscountPercentage())
                .isActive(isActive())
                .build();
    }

}
