package source_files.services.entityServices.abstracts.userAbstract;


import source_files.data.models.baseEntities.UserEntity;

import java.util.List;

public interface UserEntityService {
    UserEntity create(UserEntity userEntity);

    UserEntity update(UserEntity userEntity);

    UserEntity getById(int id);

    UserEntity getByPhoneNumber(String phoneNumber);

    UserEntity getByEmailAddress(String emailAddress);

    List<UserEntity> getAll();

    List<UserEntity> getAllByDeletedState(boolean isDeleted);

    void delete(UserEntity userEntity);
}
