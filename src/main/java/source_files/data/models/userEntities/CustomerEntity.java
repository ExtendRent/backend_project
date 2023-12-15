package source_files.data.models.userEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.types.DrivingLicenseType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
//@SuperBuilder
public class CustomerEntity extends UserEntity {

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "driving_license_number", unique = true)
    private String drivingLicenseNumber;

    @Column(name = "driving_license_type")
    private DrivingLicenseType drivingLicenseType;
}