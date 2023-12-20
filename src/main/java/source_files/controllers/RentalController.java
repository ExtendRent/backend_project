package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.AddColorRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.RentalService;

@RestController
@RequestMapping("api/rentals")
@AllArgsConstructor
public class RentalController {
    private RentalService rentalService;

    @PostMapping("/add/rental")
    public ResponseEntity<TResponse<?>> addRental(@RequestBody AddRentalRequest addRentalRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.rentalService.add(addRentalRequest))
                .message("Kiralama işlemi başarılı")
                .build()
        );
    }
}
