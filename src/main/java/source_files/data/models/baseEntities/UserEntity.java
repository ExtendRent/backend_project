package source_files.data.models.baseEntities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import source_files.data.Status.DefaultUserStatus;
import source_files.data.types.userTypes.UserRole;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
@Builder
//@MappedSuperclass//->Bu sınıf entity olduğu için bu anotasyonu KULLANAMAYIZ(artık bir üst sınıftaki kolonlar da bu sınıfa gelecek). !!!
@Inheritance(strategy = InheritanceType.JOINED)
//-> kendini extend eden her klasa kendi değişkenlerini eklemesini sağlar.
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;
    @Column(name = "email_address", unique = true)
    private String emailAddress;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "password")
    private String password;
    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private UserRole authority;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DefaultUserStatus status;

    @Column(name = "image_path")
    private String imagePath;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return this.emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return switch (this.status) {
            case BLOCKED, TIME_BLOCKED -> false;
            default -> true;
        };
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
