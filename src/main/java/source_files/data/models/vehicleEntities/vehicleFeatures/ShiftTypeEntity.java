package source_files.data.models.vehicleEntities.vehicleFeatures;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.Item;
import source_files.data.types.itemTypes.ShiftType;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shift_types")
public class ShiftTypeEntity extends Item {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "shift_type")
    @Enumerated(EnumType.STRING)
    private ShiftType shiftType;
}
