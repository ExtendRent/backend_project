package source_files.data.types.userTypes;

import org.springframework.security.core.GrantedAuthority;


public enum UserRole implements GrantedAuthority {
    ADMIN, EMPLOYEE, CUSTOMER, DEVELOPER;


    @Override
    public String getAuthority() {
        return name();
    }
}
