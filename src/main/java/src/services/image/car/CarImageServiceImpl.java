package src.services.image.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import src.core.exception.DataNotFoundException;
import src.core.exception.FileException;
import src.core.utilities.ImageUtils;
import src.data.models.image_entities.CarImageEntity;
import src.repositories.image.CarImageRepository;
import src.services.external.CloudinaryServiceImpl;
import src.services.image.ImageRules;

import java.io.IOException;
import java.util.List;

import static src.core.exception.exception_types.FileExceptionType.PHOTO_DELETE_FAILED;
import static src.core.exception.exception_types.FileExceptionType.PHOTO_UPLOAD_FAILED;
import static src.core.exception.exception_types.NotFoundExceptionType.IMAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CarImageServiceImpl implements CarImageService {

    private final CarImageRepository repository;
    private final ImageRules rules;
    private final CloudinaryServiceImpl cloudinaryServiceImpl;

    @Override
    public CarImageEntity create(MultipartFile file, String licensePlate) throws IOException {

        try {
            byte[] newByte = ImageUtils.resizeImage(file.getBytes(), 1920, 1080);
            String imageUrl = cloudinaryServiceImpl.uploadFileCar(file, licensePlate);
            byte[] decompressedData = ImageUtils.decompressImage(newByte);
            return repository.save(CarImageEntity.carImageBuilder()
                    .name(licensePlate)
                    .type(file.getContentType())
                    .url(imageUrl)
                    .imageData(ImageUtils.compressImage(decompressedData))
                    .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(PHOTO_UPLOAD_FAILED);
        }
    }


    @Override
    public byte[] downloadImage(String fileName) {
        CarImageEntity dbImageData = repository.findByName(fileName).orElse(null);
        assert dbImageData != null;
        return ImageUtils.decompressImage(dbImageData.getImageData());
    }

    @Override
    public CarImageEntity getById(int id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException(IMAGE_NOT_FOUND));
    }

    @Override
    public List<CarImageEntity> getAll() {
        List<CarImageEntity> images = repository.findAll();
        rules.checkDataList(images);
        return images;
    }

    @Override
    public void delete(int id) throws IOException {
        try {
            if (cloudinaryServiceImpl.deleteFile(this.getById(id).getName())) {
                repository.delete(this.getById(id));
            }
        } catch (Exception e) {
            throw new FileException(PHOTO_DELETE_FAILED);
        }
    }
}