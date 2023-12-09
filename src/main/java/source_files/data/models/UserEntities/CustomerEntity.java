package source_files.data.models.UserEntities;

import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.UserEntity;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@SuperBuilder
public class CustomerEntity extends UserEntity {



}