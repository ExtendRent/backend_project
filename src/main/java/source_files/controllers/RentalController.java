package source_files.controllers;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.RentalService;

@RestController
@RequestMapping("api/rentals")
@AllArgsConstructor
public class RentalController {
    private RentalService rentalService;

    @PostMapping("/add/rental")
    public ResponseEntity<TResponse<?>> addRental(@RequestBody AddRentalRequest addRentalRequest) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.rentalService.add(addRentalRequest))
                .message("Kiralama işlemi başarılı")
                .build()
        );
    }
}
