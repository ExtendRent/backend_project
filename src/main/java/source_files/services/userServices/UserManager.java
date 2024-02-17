package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.UserDTO;
import source_files.data.models.baseEntities.UserEntity;
import source_files.dataAccess.userRepositories.UserRepository;
import source_files.services.userServices.abstracts.UserService;

import java.util.List;

import static source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus.BLOCKED;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository repository;

    private final ModelMapperService mapper;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        return this.getEntity(emailAddress);
    }

    @Override
    public void createUser(UserEntity user) {
        repository.save(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.forResponse().map(entity, UserDTO.class)).toList();
    }

    @Override
    public UserDTO getById(int id) {
        return mapper.forResponse().map(getEntity(id), UserDTO.class);
    }

    @Override
    public void blockUser(int id) {
        UserEntity user = getEntity(id);
        user.setStatus(BLOCKED);
        repository.save(user);
    }

    @Override
    public List<UserDTO> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted).stream()
                .map(entity -> mapper.forResponse().map(entity, UserDTO.class)).toList();
    }

    private UserEntity getEntity(int id) {
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    private UserEntity getEntity(String emailAddress) {
        return repository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}