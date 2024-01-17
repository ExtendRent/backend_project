package source_files.data.models.userEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.types.userTypes.EmployeeType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@SuperBuilder
@Table(name = "employees")
public class EmployeeEntity extends UserEntity {


    @Column(name = "salary")
    private double salary;

    @Column(name = "employee_type")
    private EmployeeType employeeType;
}
