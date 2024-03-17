package src.service.image.user;

import org.springframework.web.multipart.MultipartFile;
import src.repository.image.UserImageEntity;

import java.io.IOException;
import java.util.List;

public interface UserImageService {
    int create(MultipartFile file, String emailAddress) throws IOException;

    byte[] downloadImage(String name);

    UserImageEntity getById(int id);

    Integer getIdByName(String name);

    List<UserImageEntity> getAll();

    void delete(int id);

}
