package src.repository.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import src.core.exception.DataNotFoundException;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.USER_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {
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
    public Page<UserEntity> getAll(Pageable pageable) {

        return this.userRepository.findAll(pageable);
    }

    @Override
    public List<UserEntity> getAllByDeletedState(boolean isDeleted) {
        return this.userRepository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return userRepository.countByIsDeleted(isDeleted);
    }

    @Override
    public void delete(UserEntity userEntity) {
        this.userRepository.delete(userEntity);
    }

    @Override
    public UserEntity getByEmailAddress(String emailAddress) {
        return this.userRepository.findByEmailAddressIgnoreCase(emailAddress).orElseThrow(() -> new DataNotFoundException(
                USER_DATA_NOT_FOUND));
    }
}
