package source_files.controllers.paperWorkControllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.DTO.paperWorkDTOs.ShowRentalResponse;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.data.requests.paperworkRequests.RentalRequests.CreateRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ReturnRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ShowRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.UpdateRentalRequest;
import source_files.data.responses.TResponse;
import source_files.exception.PaymentException;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;
import source_files.services.paperWorkServices.abstracts.PaymentService;
import source_files.services.paperWorkServices.abstracts.RentalService;

import java.util.List;

import static source_files.exception.exceptionTypes.PaymentExceptionType.NOT_SUPPORTED_PAYMENT_TYPE;
import static source_files.exception.exceptionTypes.PaymentExceptionType.PAYMENT_TYPE_IS_NOT_ACTIVE;

@RestController
@RequestMapping("api/v1/rentals")
@AllArgsConstructor
@Validated
@CrossOrigin
public class RentalsController {
    PaymentTypeEntityService paymentTypeService;
    private RentalService rentalService;
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> createRental(
            @Valid @RequestBody CreateRentalRequest createRentalRequest) {
        PaymentTypeEntity paymentTypeEntity = paymentTypeService.getById(createRentalRequest.getPaymentTypeId());

        if (paymentTypeEntity.isActive()) {
            switch (paymentTypeEntity.getPaymentType()) {
                case CREDIT_CARD:
                    this.rentalService.create(this.paymentService.payWithCreditCard(createRentalRequest));
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                case CASH:
                    break;
                case BANK_MONEY_TRANSFER:
                    break;
            }
            throw new PaymentException(NOT_SUPPORTED_PAYMENT_TYPE);
        } else {
            throw new PaymentException(PAYMENT_TYPE_IS_NOT_ACTIVE);
        }
    }


    @PostMapping("/showRental")
    public ResponseEntity<TResponse<ShowRentalResponse>> showRental(@Valid @RequestBody ShowRentalRequest showRentalRequest) {
        return new ResponseEntity<>(TResponse.<ShowRentalResponse>tResponseBuilder()
                .response(this.rentalService.showRentalDetails(showRentalRequest))
                .build(), HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<TResponse<RentalDTO>> updateRental(@RequestBody UpdateRentalRequest updateRentalRequest) {
        return new ResponseEntity<>(TResponse.<RentalDTO>tResponseBuilder()
                .response(this.rentalService.update(updateRentalRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<RentalDTO>>> getAllRentals() {
        return new ResponseEntity<>(TResponse.<List<RentalDTO>>tResponseBuilder()
                .response(this.rentalService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @PutMapping("/returnRental")
    public ResponseEntity<TResponse<?>> returnRental(@RequestBody ReturnRentalRequest returnRentalRequest) {
        return new ResponseEntity<>(TResponse.tResponseBuilder()
                .response(this.rentalService.returnCar(returnRentalRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<RentalDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<RentalDTO>>tResponseBuilder()
                .response(this.rentalService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.rentalService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
