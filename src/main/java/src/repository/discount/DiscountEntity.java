package src.repository.discount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.discount.response.DiscountResponse;
import src.core.BaseEntity;

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
    private boolean isActive;

    public DiscountResponse toModel() {
        return DiscountResponse.builder()
                .id(getId())
                .discountCode(getDiscountCode())
                .discountPercentage(getDiscountPercentage())
                .isActive(isActive())
                .build();
    }

}
