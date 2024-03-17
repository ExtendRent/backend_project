package src.repository.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserEntityService {
    @Transactional
    UserEntity create(UserEntity userEntity);

    @Transactional
    UserEntity update(UserEntity userEntity);

    @Transactional
    UserEntity getById(int id);

    @Transactional
    UserEntity getByEmailAddress(String emailAddress);

    @Transactional
    Page<UserEntity> getAll(Pageable pageable);

    @Transactional
    List<UserEntity> getAllByDeletedState(boolean isDeleted);

    int getCountByDeletedState(boolean isDeleted);

    void delete(UserEntity userEntity);
}
