package src.data.models.vehicle_entities.vehicle_features;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controllers.vehicle.responses.BrandResponse;
import src.data.models.base_entities.BaseEntity;
import src.data.models.image_entities.BrandImageEntity;

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