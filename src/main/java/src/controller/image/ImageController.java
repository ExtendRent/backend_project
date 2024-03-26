package src.controller.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import src.controller.TResponse;
import src.core.rest.BaseController;
import src.service.image.brand.BrandImageService;
import src.service.image.car.CarImageService;
import src.service.image.user.UserImageService;

import static src.controller.image.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController extends BaseController {
    private final CarImageService carImageService;
    private final UserImageService userImageService;
    private final BrandImageService brandImageService;

    @PostMapping(value = "/car", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TResponse<Integer>> uploadCarImage(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestParam("licensePlate") String licensePlate) {
        log.info(UPLOADING_CAR_IMAGE, licensePlate);
        Integer response = carImageService.create(image, licensePlate).getId();
        log.info(CAR_IMAGE_UPLOADED_SUCCESSFULLY, licensePlate);
        return answer(response, HttpStatus.OK);
    }

    @PostMapping(value = "/user", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TResponse<Integer>> uploadUserImage(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "emailAddress", required = false) String emailAddress){
        log.info(UPLOADING_USER_IMAGE, emailAddress);
        Integer response = userImageService.create(image, emailAddress);
        log.info(USER_IMAGE_UPLOADED_SUCCESSFULLY, emailAddress);
        return answer(response, HttpStatus.OK);
    }

    @PostMapping(value = "/brand", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TResponse<Integer>> uploadBrandImage(
            @RequestPart("image") MultipartFile image,
            @RequestParam("brandName") String brandName){
        log.info(UPLOADING_BRAND_IMAGE, brandName);
        Integer response = brandImageService.create(image, brandName).getId();
        log.info(BRAND_IMAGE_UPLOADED_SUCCESSFULLY, brandName);
        return answer(response, HttpStatus.OK);
    }
}
