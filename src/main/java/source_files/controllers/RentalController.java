package source_files.controllers;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.itemRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.itemRequests.RentalRequests.UpdateRentalRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.RentalService;

@RestController
@RequestMapping("api/rental")
@AllArgsConstructor
public class RentalController {
    private RentalService rentalService;

    @PostMapping("/add")
    public ResponseEntity<TResponse<?>> addRental(@RequestBody AddRentalRequest addRentalRequest) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.rentalService.add(addRentalRequest))
                .message("Kiralama işlemi başarılı")
                .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<TResponse<?>> updateRental(@RequestBody UpdateRentalRequest updateRentalRequest) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.rentalService.update(updateRentalRequest))
                .message("Kiralama işlemi başarılı")
                .build()
        );
    }

    @PutMapping("/returnCar")
    public ResponseEntity<TResponse<?>> updateRental(@RequestBody ReturnRentalRequest returnRentalRequest) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.rentalService.returnCar(returnRentalRequest))
                .message("Kiralama işlemi başarılı")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAllRentals() throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.rentalService.getAll())
                .message("Kiralama kayıtları görüntülendi")
                .build()
        );
    }
}
