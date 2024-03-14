package source_files.services.user.abstracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import source_files.controllers.user.dtos.UserDTO;
import source_files.data.models.baseEntities.UserEntity;

import java.util.List;

public interface UserService extends UserDetailsService {
    void createUser(UserEntity user);

    Page<UserDTO> getAll(Pageable pageable);

    UserDTO getById(int id);

    void blockUser(int id);

    List<UserDTO> getAllByDeletedState(boolean isDeleted);

    int getCountByDeletedState(boolean isDeleted);

    void updatePassword(int id, String password);

}
