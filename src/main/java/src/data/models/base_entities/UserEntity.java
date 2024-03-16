package src.data.models.base_entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import src.controllers.user.responses.UserResponse;
import src.data.enums.default_data_enums.status.DefaultUserStatus;
import src.data.enums.types.user_types.UserRole;
import src.data.enums.types.user_types.UserType;
import src.data.models.image_entities.UserImageEntity;

import java.util.Collection;
import java.util.List;

import static src.data.enums.default_data_enums.status.DefaultUserStatus.BLOCKED;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "userBuilder")
@Inheritance(strategy = InheritanceType.JOINED)
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
        return status;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !getIsDeleted();
    }

    public UserResponse toUserModel() {
        return UserResponse.builder()
                .id(getId())
                .name(getName())
                .surname(getSurname())
                .email(getEmailAddress())
                .userImageEntityUrl(getUserImageEntity().getUrl())
                .isDeleted(getIsDeleted())
                .authority(getAuthority())
                .status(getStatus().getLabel())
                .build();
    }
}
