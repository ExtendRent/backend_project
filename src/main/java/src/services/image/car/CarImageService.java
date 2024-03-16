package src.services.image.car;

import org.springframework.web.multipart.MultipartFile;
import src.data.models.image_entities.CarImageEntity;

import java.io.IOException;
import java.util.List;

public interface CarImageService {

    CarImageEntity create(MultipartFile file, String licensePlate) throws IOException;

    byte[] downloadImage(String name);

    CarImageEntity getById(int id);

    List<CarImageEntity> getAll();

    void delete(int id) throws IOException;
}
