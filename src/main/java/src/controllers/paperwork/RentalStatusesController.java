package src.controllers.paperwork;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.controllers.paperwork.responses.RentalStatusResponse;
import src.data.global_responses.TResponse;
import src.services.paperwork.rental.RentalStatusService;

import java.util.List;

@RestController
@RequestMapping("api/v1/rentalStatuses")
@AllArgsConstructor
@Validated
@CrossOrigin
public class RentalStatusesController {

    private final RentalStatusService service;

    @GetMapping
    public ResponseEntity<TResponse<List<RentalStatusResponse>>> getAll() {
        return new ResponseEntity<>(
                TResponse.<List<RentalStatusResponse>>tResponseBuilder().response(service.getAll()).build()
                , HttpStatus.OK
        );
    }

}
