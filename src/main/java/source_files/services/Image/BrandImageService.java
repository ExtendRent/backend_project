package source_files.services.Image;

import org.springframework.web.multipart.MultipartFile;
import source_files.data.models.imageEntities.BrandImageEntity;

import java.io.IOException;
import java.util.List;

public interface BrandImageService {
    BrandImageEntity create(MultipartFile file, String brandName) throws IOException;

    byte[] downloadImage(String name);

    BrandImageEntity getById(int id);

    List<BrandImageEntity> getAll();

    void delete(int id);
}
