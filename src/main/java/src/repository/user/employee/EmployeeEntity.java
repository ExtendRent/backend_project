package src.repository.user.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.user.employee.response.EmployeeResponse;
import src.repository.user.UserEntity;

import static src.service.user.model.UserRole.EMPLOYEE;

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

    public EmployeeResponse toModel() {
        return EmployeeResponse.builder()
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
