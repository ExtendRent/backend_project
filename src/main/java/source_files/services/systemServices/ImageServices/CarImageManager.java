package source_files.services.systemServices.ImageServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import source_files.data.models.imageEntities.CarImageEntity;
import source_files.dataAccess.imageRepositories.CarImageRepository;
import source_files.exception.DataNotFoundException;
import source_files.exception.FileException;
import source_files.services.BusinessRules.ImageBusinessRules;
import source_files.services.externalServices.CloudinaryService;
import source_files.utilities.ImageUtils;

import java.io.IOException;
import java.util.List;

import static source_files.exception.exceptionTypes.FileExceptionType.PHOTO_DELETE_FAILED;
import static source_files.exception.exceptionTypes.FileExceptionType.PHOTO_UPLOAD_FAILED;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.IMAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CarImageManager implements CarImageService {

    private final CarImageRepository repository;
    private final ImageBusinessRules rules;
    private final CloudinaryService cloudinaryService;

    @Override
    public CarImageEntity create(MultipartFile file, String licensePlate) throws IOException {

        try {
            byte[] newByte = ImageUtils.resizeImage(file.getBytes(), 1920, 1080);
            String imageUrl = cloudinaryService.uploadFileCar(file, licensePlate);
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
            if (cloudinaryService.deleteFile(this.getById(id).getUrl())) {
                repository.delete(this.getById(id));
            }
        } catch (Exception e) {
            throw new FileException(PHOTO_DELETE_FAILED);
        }
    }
}