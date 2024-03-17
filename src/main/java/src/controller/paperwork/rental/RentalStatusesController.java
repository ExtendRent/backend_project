package src.controller.paperwork.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.controller.TResponse;
import src.controller.paperwork.rental.responses.RentalStatusResponse;
import src.service.paperwork.rental.status.RentalStatusService;

import java.util.List;

@RestController
@RequestMapping("api/v1/rentalStatuses")
@RequiredArgsConstructor
@Validated

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
