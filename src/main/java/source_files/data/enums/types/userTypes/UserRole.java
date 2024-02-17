package source_files.data.enums.types.userTypes;

import org.springframework.security.core.GrantedAuthority;


public enum UserRole implements GrantedAuthority {
    ADMIN("Admin"),
    EMPLOYEE("Çalışan"),
    CUSTOMER("Müşteri"),
    DEVELOPER("Geliştirici");
    private final String label;
    @Override
    public String getAuthority() {
        return name();
    }

    public String getLabel() {
        return name();
    }
    UserRole(String label) {
        this.label = label;
    }
}
