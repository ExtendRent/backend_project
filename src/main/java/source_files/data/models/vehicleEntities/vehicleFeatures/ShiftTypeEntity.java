package source_files.data.models.vehicleEntities.vehicleFeatures;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.Item;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shift_types")
public class ShiftTypeEntity extends Item {


    @Column(name = "shift_type")
    private String shiftType;

    @Column(name = "name", unique = true)
    private String name;
}
