package src.services.image.brand;

import org.springframework.web.multipart.MultipartFile;
import src.data.models.image_entities.BrandImageEntity;

import java.io.IOException;
import java.util.List;

public interface BrandImageService {
    BrandImageEntity create(MultipartFile file, String brandName) throws IOException;

    byte[] downloadImage(String name);

    BrandImageEntity getById(int id);

    List<BrandImageEntity> getAll();

    void delete(int id);
}
