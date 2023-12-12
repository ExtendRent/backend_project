package source_files.data.models.baseEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.types.UserType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
//@MappedSuperclass//->Bu sınıf entity olduğu için bu anotasyonu KULLANAMAYIZ(artık bir üst sınıftaki kolonlar da bu sınıfa gelecek). !!!
@Inheritance(strategy = InheritanceType.JOINED)
//-> kendini extend eden her klasa kendi değişkenlerini eklemesini sağlar.
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "user_type")
    private UserType userType;

}
