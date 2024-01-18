package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.DTO.paperWorkDTOs.ShowRentalResponse;
import source_files.data.requests.paperworkRequests.RentalRequests.AddRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ShowRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.UpdateRentalRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.PaymentService;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;
import source_files.services.paperWorkServices.abstracts.RentalService;

import java.util.List;

@RestController
@RequestMapping("api/v1/rentals")
@AllArgsConstructor
@Validated
@CrossOrigin
public class RentalsController {
    PaymentTypeService paymentTypeService;
    private RentalService rentalService;
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<TResponse<HttpStatus>> addRental(
            @Valid @RequestBody AddRentalRequest addRentalRequest) {

        switch (this.paymentTypeService.getById(addRentalRequest.getPaymentTypeId()).getPaymentType()) {
            case CREDIT_CARD:
                this.rentalService.add(this.paymentService.payWithCreditCard(addRentalRequest));
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            case CASH:
                break;
            case BANK_MONEY_TRANSFER:
                break;
        }
        throw new RuntimeException("Payment type not supported");
    }

    @PostMapping("/showRental")
    public ResponseEntity<TResponse<ShowRentalResponse>> showRental(@Valid @RequestBody ShowRentalRequest showRentalRequest) {
        return ResponseEntity.ok(TResponse.<ShowRentalResponse>tResponseBuilder()
                .response(this.rentalService.showRentalDetails(showRentalRequest))
                .message("Kiralama önizlemesi gösterildi")
                .build()
        );
    }

    @PutMapping
    public ResponseEntity<TResponse<RentalDTO>> updateRental(@RequestBody UpdateRentalRequest updateRentalRequest) {
        return ResponseEntity.ok(TResponse.<RentalDTO>tResponseBuilder()
                .response(this.rentalService.update(updateRentalRequest))
                .message("Güncelleme işlemi başarılı")
                .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<RentalDTO>>> getAllRentals() {
        return ResponseEntity.ok(TResponse.<List<RentalDTO>>tResponseBuilder()
                .response(this.rentalService.getAll())
                .message("Kiralama kayıtları görüntülendi")
                .build()
        );
    }

    @PutMapping("/returnRental")
    public ResponseEntity<TResponse<?>> returnRental(@RequestBody ReturnRentalRequest returnRentalRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.rentalService.returnCar(returnRentalRequest))
                .message("Kiralama işlemi başarılı")
                .build()
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<RentalDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return ResponseEntity.ok(TResponse.<List<RentalDTO>>tResponseBuilder()
                .response(this.rentalService.getAllByDeletedState(isDeleted))
                .message("Araba Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<HttpStatus> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.rentalService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
