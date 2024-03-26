package src.service.image.brand;

import org.springframework.web.multipart.MultipartFile;
import src.repository.image.BrandImageEntity;

import java.util.List;

public interface BrandImageService {
    BrandImageEntity create(MultipartFile file, String brandName);

    byte[] downloadImage(String name);

    BrandImageEntity getById(int id);

    List<BrandImageEntity> getAll();

    void delete(int id);
}
