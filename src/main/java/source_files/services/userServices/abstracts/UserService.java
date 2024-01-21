package source_files.services.userServices.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;
import source_files.data.models.baseEntities.UserEntity;

public interface UserService extends UserDetailsService {
    void createUser(UserEntity user);
}
