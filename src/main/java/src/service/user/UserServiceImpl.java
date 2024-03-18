package src.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.controller.user.responses.UserResponse;
import src.repository.user.UserEntity;
import src.repository.user.UserEntityService;

import java.util.List;

import static src.service.user.model.DefaultUserStatus.BLOCKED;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserEntityService entityService;
    private final UserRules rules;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        rules.checkEmail(emailAddress);
        return entityService.getByEmailAddress(emailAddress);
    }

    @Override
    public void createUser(UserEntity user) {
        entityService.create(user);
    }

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        Page<UserResponse> users = entityService.getAll(pageable).map(UserEntity::toUserModel);
        rules.checkDataList(users.toList());
        return users;
    }

    @Override
    public UserResponse getById(int id) {
        return entityService.getById(id).toUserModel();
    }

    @Override
    public void blockUser(int id) {
        UserEntity user = entityService.getById(id);
        user.setStatus(BLOCKED);
        entityService.update(user);
    }

    @Transactional
    @Override
    public List<UserResponse> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return entityService.getCountByDeletedState(isDeleted);
    }

    @Override
    public void updatePassword(int id, String password) {
        UserEntity userEntity = entityService.getById(id);
        userEntity.setPassword(passwordEncoder.encode(password));
        entityService.update(userEntity);
    }

    private List<UserResponse> mapToDTOList(List<UserEntity> entities) {
        rules.checkDataList(entities);
        return entities.stream().map(UserEntity::toUserModel).toList();
    }
}