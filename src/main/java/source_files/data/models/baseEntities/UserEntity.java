package source_files.data.models.baseEntities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus;
import source_files.data.enums.types.userTypes.UserRole;
import source_files.data.enums.types.userTypes.UserType;
import source_files.data.models.imageEntities.UserImageEntity;

import java.util.Collection;
import java.util.List;

import static source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus.BLOCKED;


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
    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private UserRole authority;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

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

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DefaultUserStatus status;
    @ManyToOne
    @JoinColumn(name = "image_id")
    private UserImageEntity userImageEntity;

    public DefaultUserStatus getStatus() {
        return this.status;
    }

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
        return this.status != BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !getIsDeleted();
    }
}
