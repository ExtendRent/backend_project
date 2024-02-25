package source_files.data.models.userEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import source_files.data.models.baseEntities.UserEntity;

import static source_files.data.enums.types.userTypes.UserRole.EMPLOYEE;

@Getter
@Setter
@AllArgsConstructor
@Entity
//@SuperBuilder
@Table(name = "employees")
public class EmployeeEntity extends UserEntity {

    @Column(name = "salary")
    private double salary;

    public EmployeeEntity() {
        this.setAuthority(EMPLOYEE);
    }
}
