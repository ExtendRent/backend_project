package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.UserDTO;
import source_files.data.models.baseEntities.UserEntity;
import source_files.dataAccess.userRepositories.UserRepository;
import source_files.services.BusinessRules.userBusinessRuless.UserBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.userServices.abstracts.UserService;

import java.util.List;

import static source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus.BLOCKED;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository repository;

    private final UserEntityService entityService;
    private final ModelMapperService mapper;

    private final UserBusinessRules rules;

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
        Page<UserDTO> users = entityService.getAll(pageable).map(this::mapToDto);
        rules.checkDataList(users.toList());
        return users;
    }

    @Override
    public UserDTO getById(int id) {
        return mapToDto(entityService.getById(id));
    }

    @Override
    public void blockUser(int id) {
        UserEntity user = entityService.getById(id);
        user.setStatus(BLOCKED);
        repository.save(user);
    }

    @Override
    public List<UserDTO> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted).stream()
                .map(entity -> mapper.forResponse().map(entity, UserDTO.class)).toList();
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return entityService.getCountByDeletedState(isDeleted);
    }

    private UserDTO mapToDto(UserEntity user) {
        return mapper.forResponse().map(user, UserDTO.class);
    }
}