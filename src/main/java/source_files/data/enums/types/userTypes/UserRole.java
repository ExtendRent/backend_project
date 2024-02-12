package source_files.data.enums.types.userTypes;

import org.springframework.security.core.GrantedAuthority;


public enum UserRole implements GrantedAuthority {
    ADMIN, EMPLOYEE, CUSTOMER, DEVELOPER;


    @Override
    public String getAuthority() {
        return name();
    }
}
