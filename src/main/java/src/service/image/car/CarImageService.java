package src.service.image.car;

import org.springframework.web.multipart.MultipartFile;
import src.repository.image.CarImageEntity;

import java.io.IOException;
import java.util.List;

public interface CarImageService {

    CarImageEntity create(MultipartFile file, String licensePlate) throws IOException;

    byte[] downloadImage(String name);

    CarImageEntity getById(int id);

    List<CarImageEntity> getAll();

    void delete(int id) throws IOException;
}
