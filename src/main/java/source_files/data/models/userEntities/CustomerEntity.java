package source_files.data.models.userEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.types.userTypes.CustomerType;
import source_files.data.types.itemTypes.DrivingLicenseType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    @Column(name = "driving_license_type")
    private List<DrivingLicenseType> drivingLicenseTypes;
}