package source_files.data.models.vehicleEntities.vehicleFeatures;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.controllers.vehicle.dtos.BrandDTO;
import source_files.data.models.baseEntities.BaseEntity;
import source_files.data.models.imageEntities.BrandImageEntity;

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

    public BrandDTO toModel() {
        return BrandDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .brandImageEntityId(this.getBrandImageEntity().getId())
                .isDeleted(this.getIsDeleted())
                .brandImageEntityUrl(this.getBrandImageEntity().getImageUrl())
                .build();
    }
}