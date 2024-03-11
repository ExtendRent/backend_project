package source_files.data.models.userEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.enums.types.userTypes.CustomerType;
import source_files.data.models.DrivingLicenseTypeEntity;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;

import java.util.List;

import static source_files.data.enums.types.userTypes.UserRole.CUSTOMER;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder(builderMethodName = "customerBuilder")
@Table(name = "customers")
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

    public CustomerDTO toModel() {
        return CustomerDTO.builder()
                .id(getId())
                .drivingLicenseTypeId(getDrivingLicenseTypeEntity().getId())
                .userImageEntityId(getUserImageEntity().getId())
                .phoneNumber(getPhoneNumber())
                .drivingLicenseNumber(getDrivingLicenseNumber())
                .drivingLicenseTypeEntityName(getDrivingLicenseTypeEntity().getName())
                .name(getName())
                .surname(getSurname())
                .emailAddress(getEmailAddress())
                .userImageEntityImageUrl(getUserImageEntity().getUrl())
                .isDeleted(getIsDeleted())
                .authority(getAuthority().getLabel())
                .status(getStatus().getLabel())
                .build();
    }

    @PrePersist
    protected void beforeCreate() {
        super.beforeCreate();  // BaseEntity sınıfındaki beforeCreate metodu çağırdık.
        this.setAuthority(CUSTOMER);
    }

    @PreUpdate
    protected void beforeUpdate() {
        super.beforeUpdate();
        this.setAuthority(CUSTOMER);
    }
}