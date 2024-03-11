package source_files.data.models.paperWorkEntities.paymentEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.paperWorkDTOs.PaymentTypeDTO;
import source_files.data.enums.defaultDataEnums.DefaultPaymentType;
import source_files.data.models.baseEntities.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder(builderMethodName = "paymentTypeBuilder")
@Table(name = "payment_type")
public class PaymentTypeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private DefaultPaymentType paymentType;

    @Column(name = "is_active")
    private boolean isActive = true;

    public PaymentTypeDTO toModel() {
        return PaymentTypeDTO.builder()
                .id(getId())
                .name(getName())
                .isActive(isActive())
                .build();
    }
}
