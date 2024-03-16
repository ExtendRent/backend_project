package src.data.models.paperwork_entities.rentalEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.controllers.paperwork.responses.RentalStatusResponse;
import src.data.enums.default_data_enums.status.DefaultRentalStatus;
import src.data.models.base_entities.BaseEntity;

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

    public RentalStatusResponse toModel() {
        return RentalStatusResponse.builder()
                .id(this.getId())
                .name(this.getName())
                .isDeleted(this.getIsDeleted())
                .build();
    }
}