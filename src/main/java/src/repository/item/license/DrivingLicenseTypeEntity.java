package src.repository.item.license;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.item.license.responses.DrivingLicenseTypeResponse;
import src.repository.BaseEntity;

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

    public DrivingLicenseTypeResponse toModel() {
        return DrivingLicenseTypeResponse.builder()
                .id(getId())
                .name(getName())
                .description(getDescription())
                .licenseLevel(getLicenseLevel())
                .isDeleted(getIsDeleted())
                .build();
    }
}
