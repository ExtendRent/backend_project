package src.service.image.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import src.core.exception.DataNotFoundException;
import src.core.exception.FileException;
import src.core.utilities.ImageUtils;
import src.repository.image.BrandImageEntity;
import src.repository.image.BrandImageRepository;
import src.service.external.CloudinaryServiceImpl;
import src.service.image.ImageRules;

import java.io.IOException;
import java.util.List;

import static src.core.exception.type.FileExceptionType.PHOTO_DELETE_FAILED;
import static src.core.exception.type.FileExceptionType.PHOTO_UPLOAD_FAILED;
import static src.core.exception.type.NotFoundExceptionType.IMAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BrandImageServiceImpl implements BrandImageService {
    private final BrandImageRepository repository;
    private final ImageRules rules;
    private final CloudinaryServiceImpl cloudinaryServiceImpl;

    @Override
    public BrandImageEntity create(MultipartFile file, String brandName) throws IOException {
        try {
            String imageUrl = cloudinaryServiceImpl.uploadFileBrand(file, brandName);
            return repository.save(BrandImageEntity.brandImageBuilder()
                    .name(brandName)
                    .type(file.getContentType())
                    .imageUrl(imageUrl)
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(PHOTO_UPLOAD_FAILED);
        }
    }

    @Override
    public byte[] downloadImage(String fileName) {
        BrandImageEntity dbImageData = repository.findByName(fileName).orElse(null);
        assert dbImageData != null;
        return ImageUtils.decompressImage(dbImageData.getImageData());
    }

    @Override
    public BrandImageEntity getById(int id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException(IMAGE_NOT_FOUND));
    }

    @Override
    public List<BrandImageEntity> getAll() {
        List<BrandImageEntity> brandImages = repository.findAll();
        rules.checkDataList(brandImages);
        return brandImages;
    }

    @Override
    public void delete(int id) {
        BrandImageEntity brandImage = this.getById(id);
        try {
            if (cloudinaryServiceImpl.deleteFile(brandImage.getName())) {
                repository.delete(this.getById(id));
            }
        } catch (Exception e) {
            throw new FileException(PHOTO_DELETE_FAILED);
        }
    }
}
