package source_files.controllers.paperWork;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import source_files.controllers.paperWork.dtos.RentalStatusDTO;
import source_files.data.responses.TResponse;
import source_files.services.paperWork.abstracts.RentalStatusService;

import java.util.List;

@RestController
@RequestMapping("api/v1/rentalStatuses")
@AllArgsConstructor
@Validated
@CrossOrigin
public class RentalStatusesController {

    private final RentalStatusService service;

    @GetMapping
    public ResponseEntity<TResponse<List<RentalStatusDTO>>> getAll() {
        return new ResponseEntity<>(
                TResponse.<List<RentalStatusDTO>>tResponseBuilder().response(service.getAll()).build()
                , HttpStatus.OK
        );
    }

}
