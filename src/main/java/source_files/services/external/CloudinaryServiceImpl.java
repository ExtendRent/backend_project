package source_files.services.external;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import source_files.data.enums.CloudinaryConstants;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements FileUploadService {
    private final Cloudinary cloudinary;

    @Override
    public String uploadFileCar(MultipartFile file, String uniqColumn) throws IOException {
        String publicId = CloudinaryConstants.BASE_PUBLIC_ID_CAR.getValue() + uniqColumn;

        Map params = ObjectUtils.asMap(
                CloudinaryConstants.USE_FILENAME.getValue(), file.getName(),
                CloudinaryConstants.UNIQUE_FILENAME.getValue(), true,
                CloudinaryConstants.OVERWRITE.getValue(), true,
                CloudinaryConstants.PUBLIC_ID.getValue(), publicId
        );

        return cloudinary.uploader()
                .upload(file.getBytes(), params)
                .get(CloudinaryConstants.URL.getValue())
                .toString();
    }

    @Override
    public String uploadFileUser(MultipartFile file, String uniqColumn) throws IOException {
        String publicId = CloudinaryConstants.BASE_PUBLIC_ID_USER.getValue() + uniqColumn;

        Map params = ObjectUtils.asMap(
                CloudinaryConstants.USE_FILENAME.getValue(), file.getName(),
                CloudinaryConstants.UNIQUE_FILENAME.getValue(), true,
                CloudinaryConstants.OVERWRITE.getValue(), true,
                CloudinaryConstants.PUBLIC_ID.getValue(), publicId
        );

        return cloudinary.uploader()
                .upload(file.getBytes(), params)
                .get(CloudinaryConstants.URL.getValue())
                .toString();
    }

    @Override
    public String uploadFileBrand(MultipartFile file, String uniqColumn) throws IOException {

        String publicId = CloudinaryConstants.BASE_PUBLIC_ID_BRAND.getValue() + uniqColumn;

        Map params = ObjectUtils.asMap(
                CloudinaryConstants.USE_FILENAME.getValue(), file.getName(),
                CloudinaryConstants.UNIQUE_FILENAME.getValue(), true,
                CloudinaryConstants.OVERWRITE.getValue(), true,
                CloudinaryConstants.PUBLIC_ID.getValue(), publicId
        );

        return cloudinary.uploader()
                .upload(file.getBytes(), params)
                .get(CloudinaryConstants.URL.getValue())
                .toString();
    }

    public boolean deleteFile(String uniqColumn) throws IOException {
        String publicId = CloudinaryConstants.BASE_PUBLIC_ID_CAR.getValue() + uniqColumn;

        Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        return "ok".equals(result.get("result"));
    }
}
