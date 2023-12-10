package source_files.data.models.baseEntities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass //Alt klasların database tablosuna buradaki kolonları eklemek için kullanılır.
@Table(name = "users") //TODO kontrol edilecek. eğer hata yoksa users tablosu iptal edilecek.
@Inheritance(strategy = InheritanceType.JOINED) // kendini extend eden her klasa kendi değişkenlerini eklemesini sağlar.
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "password")
    private String password;
}
