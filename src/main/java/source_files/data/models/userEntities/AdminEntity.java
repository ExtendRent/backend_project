package source_files.data.models.userEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.UserEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@SuperBuilder
@Table(name = "admins")
public class AdminEntity extends UserEntity {


    @Column(name = "salary")
    private double salary;

}