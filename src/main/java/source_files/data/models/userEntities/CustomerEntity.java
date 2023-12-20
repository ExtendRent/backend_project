package source_files.data.models.userEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.types.DrivingLicenseType;

import java.util.List;

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

    @OneToMany(mappedBy ="customerEntity")
    List<RentalEntity> rentalHistory;
}