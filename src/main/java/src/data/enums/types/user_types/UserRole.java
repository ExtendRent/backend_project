package src.data.enums.types.user_types;

import org.springframework.security.core.GrantedAuthority;


public enum UserRole implements GrantedAuthority {
    ADMIN("Admin"),
    EMPLOYEE("Çalışan"),
    CUSTOMER("Müşteri"),
    DEVELOPER("Geliştirici");
    private final String label;

    UserRole(String label) {
        this.label = label;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public String getLabel() {
        return name();
    }
}
