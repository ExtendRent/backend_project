package src.repository.vehicle.features.common.brand;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.vehicle.features.common.brand.response.BrandResponse;
import src.repository.BaseEntity;
import src.repository.image.BrandImageEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "brandBuilder")
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private BrandImageEntity brandImageEntity;

    public BrandResponse toModel() {
        return BrandResponse.builder()
                .id(this.getId())
                .name(this.getName())
                .brandImageEntityId(this.getBrandImageEntity().getId())
                .isDeleted(this.getIsDeleted())
                .brandImageEntityUrl(this.getBrandImageEntity().getImageUrl())
                .build();
    }
}