package src.controller.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import src.service.image.brand.BrandImageService;
import src.service.image.car.CarImageService;
import src.service.image.user.UserImageService;

import java.io.IOException;

import static src.controller.image.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final CarImageService carImageService;
    private final UserImageService userImageService;
    private final BrandImageService brandImageService;

    @PostMapping(value = "/car", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> uploadCarImage(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestParam("licensePlate") String licensePlate) throws IOException {
        log.info(UPLOADING_CAR_IMAGE, licensePlate);
        Integer imageId = carImageService.create(image, licensePlate).getId();
        log.info(CAR_IMAGE_UPLOADED_SUCCESSFULLY, licensePlate);
        return new ResponseEntity<>(imageId, HttpStatus.OK);
    }


    @PostMapping(value = "/user", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> uploadUserImage(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "emailAddress", required = false) String emailAddress) throws IOException {
        log.info(UPLOADING_USER_IMAGE, emailAddress);
        Integer imageId = userImageService.create(image, emailAddress);
        log.info(USER_IMAGE_UPLOADED_SUCCESSFULLY, emailAddress);
        return new ResponseEntity<>(imageId, HttpStatus.OK);
    }

    @PostMapping(value = "/brand", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> uploadBrandImage(
            @RequestPart("image") MultipartFile image,
            @RequestParam("brandName") String brandName) throws IOException {
        log.info(UPLOADING_BRAND_IMAGE, brandName);
        Integer imageId = brandImageService.create(image, brandName).getId();
        log.info(BRAND_IMAGE_UPLOADED_SUCCESSFULLY, brandName);
        return new ResponseEntity<>(imageId, HttpStatus.OK);
    }
}
