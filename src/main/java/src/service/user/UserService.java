package src.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import src.controller.user.response.UserResponse;
import src.repository.user.UserEntity;

import java.util.List;

public interface UserService extends UserDetailsService {
    void createUser(UserEntity user);

    Page<UserResponse> getAll(Pageable pageable);

    UserResponse getById(int id);

    void blockUser(int id);

    List<UserResponse> getAllByDeletedState(boolean isDeleted);

    int getCountByDeletedState(boolean isDeleted);

    void updatePassword(int id, String password);

}
