package source_files.data.types.userTypes;

import org.springframework.security.core.GrantedAuthority;
import source_files.data.types.BaseType;

public enum UserType implements BaseType, GrantedAuthority {
    ADMIN, EMPLOYEE, CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
