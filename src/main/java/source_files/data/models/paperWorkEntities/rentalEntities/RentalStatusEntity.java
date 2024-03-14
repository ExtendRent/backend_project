package source_files.data.models.paperWorkEntities.rentalEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.controllers.paperWork.dtos.RentalStatusDTO;
import source_files.data.enums.defaultDataEnums.Status.DefaultRentalStatus;
import source_files.data.models.baseEntities.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rental_statuses")
public class RentalStatusEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DefaultRentalStatus status;

    public RentalStatusDTO toModel() {
        return RentalStatusDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .isDeleted(this.getIsDeleted())
                .build();
    }
}