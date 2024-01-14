package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.paperWorkDTOs.PaymentDetailsDTO;
import source_files.data.requests.paperworkRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.UpdateRentalRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.RentalService;

@RestController
@RequestMapping("api/rental")
@AllArgsConstructor
@Validated
public class RentalController {
    private RentalService rentalService;

    @PostMapping("/add")
    public ResponseEntity<TResponse<?>> addRental(@Valid @RequestBody AddRentalRequest addRentalRequest, @RequestBody PaymentDetailsDTO paymentDetailsDTO) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.rentalService.add(addRentalRequest, paymentDetailsDTO))
                .message("Kiralama işlemi başarılı")
                .build()
        );
    }

    @PostMapping("/showRental")
    public ResponseEntity<TResponse<?>> showRental(@Valid @RequestBody AddRentalRequest addRentalRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.rentalService.showRentalDetails(addRentalRequest))
                .message("Kiralama kaydı görüntülendi")
                .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<TResponse<?>> updateRental(@RequestBody UpdateRentalRequest updateRentalRequest) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.rentalService.update(updateRentalRequest))
                .message("Kiralama işlemi başarılı")
                .build()
        );
    }

    @PutMapping("/returnCar")
    public ResponseEntity<TResponse<?>> updateRental(@RequestBody ReturnRentalRequest returnRentalRequest) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.rentalService.returnCar(returnRentalRequest))
                .message("Kiralama işlemi başarılı")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAllRentals() throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.rentalService.getAll())
                .message("Kiralama kayıtları görüntülendi")
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> deleteRental(@Valid @PathVariable int id) throws BadRequestException {
        this.rentalService.delete(id);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .message("Kiralama kaydı silindi")
                .build()
        );
    }
}
