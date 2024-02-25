package source_files.data.models.userEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import source_files.data.enums.types.userTypes.CustomerType;
import source_files.data.models.DrivingLicenseTypeEntity;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;

import java.util.List;

import static source_files.data.enums.types.userTypes.UserRole.CUSTOMER;

@Getter
@Setter
@AllArgsConstructor
@Entity
//@SuperBuilder
@Table(name = "customers")
//todo: Hoca UserEntityyi extends etmedi onun yerine baseEntity extend etti yönetimesi zor dedi User diye bir alan açtı User user ?
//todo : CorporateCustomer diye bir class dha tanımlandı bizde gerek var mı ?
public class CustomerEntity extends UserEntity {

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER)
    List<RentalEntity> rentalHistory;

    @Column(name = "customer_type")
    CustomerType customerType;

    @Column(name = "driving_license_number", unique = true)
    private String drivingLicenseNumber;

    @ManyToOne()
    @JoinColumn(name = "driving_license_type")
    private DrivingLicenseTypeEntity drivingLicenseTypeEntity;

    public CustomerEntity() {
        this.setAuthority(CUSTOMER);
    }
}