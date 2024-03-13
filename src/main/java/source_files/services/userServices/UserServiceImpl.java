package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.userDTOs.UserDTO;
import source_files.data.models.baseEntities.UserEntity;
import source_files.services.BusinessRules.userBusinessRuless.UserRules;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.userServices.abstracts.UserService;

import java.util.List;

import static source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus.BLOCKED;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserEntityService entityService;
    private final UserRules rules;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        return entityService.getByEmailAddress(emailAddress);
    }

    @Override
    public void createUser(UserEntity user) {
        entityService.create(user);
    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        Page<UserDTO> users = entityService.getAll(pageable).map(UserEntity::toUserModel);
        rules.checkDataList(users.toList());
        return users;
    }

    @Override
    public UserDTO getById(int id) {
        return entityService.getById(id).toUserModel();
    }

    @Override
    public void blockUser(int id) {
        UserEntity user = entityService.getById(id);
        user.setStatus(BLOCKED);
        entityService.update(user);
    }

    @Override
    public List<UserDTO> getAllByDeletedState(boolean isDeleted) {
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

    private List<UserDTO> mapToDTOList(List<UserEntity> entities) {
        rules.checkDataList(entities);
        return entities.stream().map(UserEntity::toUserModel).toList();
    }
}