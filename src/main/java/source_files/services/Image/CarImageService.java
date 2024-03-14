package source_files.services.Image;

import org.springframework.web.multipart.MultipartFile;
import source_files.data.models.imageEntities.CarImageEntity;

import java.io.IOException;
import java.util.List;

public interface CarImageService {

    CarImageEntity create(MultipartFile file, String licensePlate) throws IOException;

    byte[] downloadImage(String name);

    CarImageEntity getById(int id);

    List<CarImageEntity> getAll();

    void delete(int id) throws IOException;
}
