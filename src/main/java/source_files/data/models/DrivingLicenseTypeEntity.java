package source_files.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.DrivingLicenseTypeDTO;
import source_files.data.models.baseEntities.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "drivingLicenseTypeBuilder")
@Table(name = "driving_license_types")
public class DrivingLicenseTypeEntity extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "license_level")
    int licenseLevel;

    public DrivingLicenseTypeDTO toModel() {
        return DrivingLicenseTypeDTO.builder()
                .id(getId())
                .name(getName())
                .description(getDescription())
                .licenseLevel(getLicenseLevel())
                .isDeleted(getIsDeleted())
                .build();
    }
}
