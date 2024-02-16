package source_files.services.systemServices.ImageServices;

import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import source_files.data.enums.defaultDataEnums.DefaultUserImageUrl;
import source_files.data.models.imageEntities.UserImageEntity;
import source_files.dataAccess.imageRepositories.UserImageRepository;
import source_files.exception.DataNotFoundException;
import source_files.exception.FileException;
import source_files.services.BusinessRules.ImageBusinessRules;
import source_files.services.externalServices.CloudinaryService;
import source_files.utilities.ImageUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static source_files.exception.exceptionTypes.FileExceptionType.PHOTO_DELETE_FAILED;
import static source_files.exception.exceptionTypes.FileExceptionType.PHOTO_UPLOAD_FAILED;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.IMAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserImageManager implements UserImageService {
    private final UserImageRepository repository;

    private final CloudinaryService cloudinaryService;
    private final ImageBusinessRules rules;

    @Override
    public UserImageEntity create(MultipartFile file, String emailAddress) throws IOException {
        if (file == null) {
            DefaultUserImageUrl defaultUserImage = DefaultUserImageUrl.DEFAULT_USER_IMAGE;
            URL url = new URL(defaultUserImage.getUrl());
            Path path = Paths.get("src/main/assets/default/user", defaultUserImage.name() + ".jpg");
            if (Files.exists(path)) {
                Files.delete(path);
            }
            Files.copy(url.openStream(), path);
            file = new MockMultipartFile(
                    defaultUserImage.name(), path.getFileName().toString(), "image/jpeg", Files.readAllBytes(path));
        }
        try {
            byte[] newByte = ImageUtils.resizeImage(file.getBytes(), 400, 400);
            String imageUrl = cloudinaryService.uploadFileUser(file, emailAddress);
            byte[] decompressedData = ImageUtils.decompressImage(newByte);
            return repository.save(UserImageEntity.builder()
                    .name(emailAddress)
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
        Optional<UserImageEntity> dbImageData = repository.findByName(fileName);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }

    @Override
    public UserImageEntity getById(int id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException(IMAGE_NOT_FOUND));
    }

    @Override
    public List<UserImageEntity> getAll() {
        List<UserImageEntity> images = repository.findAll();
        rules.checkDataList(images);
        return images;
    }

    @Override
    public void delete(int id) {
        try {
            if (cloudinaryService.deleteFile(this.getById(id).getUrl())) {
                repository.delete(this.getById(id));
            }
        } catch (Exception e) {
            throw new FileException(PHOTO_DELETE_FAILED);
        }
    }
}