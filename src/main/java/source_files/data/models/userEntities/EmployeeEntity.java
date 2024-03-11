package source_files.data.models.userEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.userDTOs.EmployeeDTO;
import source_files.data.models.baseEntities.UserEntity;

import static source_files.data.enums.types.userTypes.UserRole.EMPLOYEE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder(builderMethodName = "employeeBuilder")
@Table(name = "employees")
public class EmployeeEntity extends UserEntity {

    @Column(name = "salary")
    private double salary;

    public EmployeeDTO toModel() {
        return EmployeeDTO.builder()
                .id(getId())
                .name(getName())
                .surname(getSurname())
                .email(getEmailAddress())
                .salary(getSalary())
                .userImageEntityImageUrl(getUserImageEntity().getUrl())
                .isDeleted(getIsDeleted())
                .authority(getAuthority())
                .build();

    }

    @PrePersist
    protected void beforeCreate() {
        super.beforeCreate();  // BaseEntity sınıfındaki beforeCreate metodu çağırdık.
        this.setAuthority(EMPLOYEE);
    }

    @PreUpdate
    protected void beforeUpdate() {
        super.beforeUpdate();
        this.setAuthority(EMPLOYEE);
    }
}
