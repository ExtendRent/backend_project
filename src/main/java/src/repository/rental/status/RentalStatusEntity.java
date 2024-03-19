package src.repository.rental.status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.controller.rental.response.RentalStatusResponse;
import src.repository.BaseEntity;
import src.service.rental.status.model.DefaultRentalStatus;

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