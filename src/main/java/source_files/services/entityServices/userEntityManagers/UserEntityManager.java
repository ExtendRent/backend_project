package source_files.services.entityServices.userEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.baseEntities.UserEntity;
import source_files.dataAccess.userRepositories.UserRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.USER_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserEntityManager implements UserEntityService {
    private final UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity userEntity) {

        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {

        return this.create(userEntity);
    }

    @Override
    public UserEntity getById(int id) {
        return this.userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(
                USER_DATA_NOT_FOUND));
    }

    @Override
    public List<UserEntity> getAll() {

        return this.userRepository.findAll();
    }

    @Override
    public List<UserEntity> getAllByDeletedState(boolean isDeleted) {
        return this.userRepository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public void delete(UserEntity userEntity) {
        this.userRepository.delete(userEntity);
    }

    @Override
    public UserEntity getByEmailAddress(String emailAddress) {
        return this.userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new DataNotFoundException(
                USER_DATA_NOT_FOUND));
    }
}
