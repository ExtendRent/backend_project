package source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.Item;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
@Table(name = "brands")
public class BrandEntity extends Item {

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "brandEntity", orphanRemoval = true)
    // orphanRemoval özelliği sayesinde, koleksiyondan çıkan varlıkların veritabanından otomatik olarak silinmesi gerçekleşir.
    @JsonIgnore
    private List<CarModelEntity> carModelEntities;

}