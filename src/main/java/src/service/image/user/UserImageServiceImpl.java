package src.service.image.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import src.core.exception.DataNotFoundException;
import src.core.exception.FileException;
import src.core.utilities.ImageUtils;
import src.repository.image.UserImageEntity;
import src.repository.image.UserImageRepository;
import src.service.external.CloudinaryServiceImpl;
import src.service.image.ImageRules;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static src.core.exception.type.FileExceptionType.PHOTO_DELETE_FAILED;
import static src.core.exception.type.FileExceptionType.PHOTO_UPLOAD_FAILED;
import static src.core.exception.type.NotFoundExceptionType.IMAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserImageServiceImpl implements UserImageService {
    private final UserImageRepository repository;
    private final CloudinaryServiceImpl cloudinaryServiceImpl;
    private final ImageRules rules;

    @Override
    public int create(MultipartFile file, String emailAddress) throws IOException {
        if (file == null) {
            return getIdByName("default_user_image");
        }
        try {
            byte[] newByte = ImageUtils.resizeImage(file.getBytes(), 400, 400);
            String url = cloudinaryServiceImpl.uploadFileUser(file, emailAddress);
            byte[] decompressedData = ImageUtils.decompressImage(newByte);
            UserImageEntity savedImage = repository.save(UserImageEntity.userImageBuilder()
                    .name(emailAddress)
                    .type(file.getContentType())
                    .url(url)
                    .imageData(ImageUtils.compressImage(decompressedData))
                    .build());
            return savedImage.getId();
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
    public Integer getIdByName(String name) {
        return repository.findIdByName(name);
    }

    @Override
    public List<UserImageEntity> getAll() {
        List<UserImageEntity> images = repository.findAll();
        rules.checkDataList(images);
        return images;
    }

    @Override
    public void delete(int id) {
        UserImageEntity userImage = this.getById(id);
        try {
            if (!userImage.getName().equals("default_user_image")) {
                cloudinaryServiceImpl.deleteFile(userImage.getName());
                repository.delete(userImage);
            }
        } catch (Exception e) {
            throw new FileException(PHOTO_DELETE_FAILED);
        }
    }

}