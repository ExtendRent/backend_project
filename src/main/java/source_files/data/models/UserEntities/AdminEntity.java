package source_files.data.models.UserEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.UserEntity;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "admins")
public class AdminEntity extends UserEntity {

    @Column(name = "salary")
    private double salary;

}