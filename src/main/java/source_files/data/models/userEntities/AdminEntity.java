package source_files.data.models.userEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.userDTOs.AdminDTO;
import source_files.data.models.baseEntities.UserEntity;

import static source_files.data.enums.types.userTypes.UserRole.ADMIN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder(builderMethodName = "adminBuilder")
@Table(name = "admins")
public class AdminEntity extends UserEntity {

    @Column(name = "salary")
    private double salary;

    public AdminDTO toModel() {
        return AdminDTO.builder()
                .id(getId())
                .name(getName())
                .surname(getSurname())
                .email(getEmailAddress())
                .phoneNumber(getPhoneNumber())
                .salary(getSalary())
                .salary(getSalary())
                .userImageEntityImageUrl(getUserImageEntity().getUrl())
                .isDeleted(getIsDeleted())
                .authority(getAuthority())
                .build();
    }

    @PrePersist
    protected void beforeCreate() {
        super.beforeCreate();  // BaseEntity sınıfındaki beforeCreate metodu çağırdık.
        this.setAuthority(ADMIN);
    }

    @PreUpdate
    protected void beforeUpdate() {
        super.beforeUpdate();
        this.setAuthority(ADMIN);
    }
}