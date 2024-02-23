package source_files.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import source_files.services.systemServices.ImageServices.BrandImageService;
import source_files.services.systemServices.ImageServices.CarImageService;
import source_files.services.systemServices.ImageServices.UserImageService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/images")
@AllArgsConstructor
@CrossOrigin
public class ImagesController {

    private final CarImageService carImageService;
    private final UserImageService userImageService;
    private final BrandImageService brandImageService;

    @PostMapping(value = "/car", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> uploadCarImage(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestParam("licensePlate") String licensePlate) throws IOException {

        return new ResponseEntity<>(carImageService.create(image, licensePlate).getId(), HttpStatus.OK);
    }

    @PostMapping(value = "/user", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> uploadUserImage(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "emailAddress", required = false) String emailAddress) throws IOException {

        return new ResponseEntity<>(userImageService.create(image, emailAddress), HttpStatus.OK);
    }

    @PostMapping(value = "/brand", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> uploadBrandImage(
            @RequestPart("image") MultipartFile image,
            @RequestParam("licensePlate") String brandName) throws IOException {

        return new ResponseEntity<>(brandImageService.create(image, brandName).getId(), HttpStatus.OK);
    }

/*
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> downloadImage(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).contentType(
                MediaType.valueOf("image/png")).body(dataService.downloadImage(name));
    }
*/

}