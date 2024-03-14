package source_files.services.external;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String uploadFileCar(MultipartFile multipartFile, String uniqColumn) throws IOException;

    String uploadFileUser(MultipartFile multipartFile, String uniqColumn) throws IOException;

    String uploadFileBrand(MultipartFile multipartFile, String uniqColumn) throws IOException;
}
