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
@Table(name = "customers")
@Entity
@SuperBuilder
public class CustomerEntity extends UserEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

}