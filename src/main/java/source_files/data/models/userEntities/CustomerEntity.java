package source_files.data.models.userEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.DrivingLicenseTypeEntity;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.types.userTypes.CustomerType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@SuperBuilder
@Table(name = "customers")
//todo: Hoca UserEntityyi extends etmedi onun yerine baseEntity extend etti yönetimesi zor dedi User diye bir alan açtı User user ?
//todo : CorporateCustomer diye bir class dha tanımlandı bizde gerek var mı ?
public class CustomerEntity extends UserEntity {

    @OneToMany(mappedBy = "customerEntity")
    List<RentalEntity> rentalHistory;

    @Column(name = "customer_type")
    CustomerType customerType;

    @Column(name = "driving_license_number", unique = true)
    private String drivingLicenseNumber;

    @ManyToOne()
    @JoinColumn(name = "driving_license_type")
    private DrivingLicenseTypeEntity drivingLicenseTypeEntity;
}