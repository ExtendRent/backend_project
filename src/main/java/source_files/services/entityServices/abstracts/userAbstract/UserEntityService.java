package source_files.services.entityServices.abstracts.userAbstract;


import org.springframework.transaction.annotation.Transactional;
import source_files.data.models.baseEntities.UserEntity;

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
    List<UserEntity> getAll();

    @Transactional
    List<UserEntity> getAllByDeletedState(boolean isDeleted);

    void delete(UserEntity userEntity);
}
