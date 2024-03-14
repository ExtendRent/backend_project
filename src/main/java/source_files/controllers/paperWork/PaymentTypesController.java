package source_files.controllers.paperWork;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.controllers.paperWork.dtos.PaymentTypeDTO;
import source_files.controllers.paperWork.requests.paymentRequests.UpdatePaymentTypeRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWork.abstracts.PaymentTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/paymentTypes")
@AllArgsConstructor
@Validated
@CrossOrigin
public class PaymentTypesController {
    private PaymentTypeService paymentTypeService;

    @PutMapping
    public ResponseEntity<TResponse<PaymentTypeDTO>> updatePaymentType(@Valid @RequestBody UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        return new ResponseEntity<>(TResponse.<PaymentTypeDTO>tResponseBuilder()
                .response(this.paymentTypeService.update(updatePaymentTypeRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<PaymentTypeDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<PaymentTypeDTO>tResponseBuilder()
                .response(paymentTypeService.getById(id)).build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<PaymentTypeDTO>>> GetAllPaymentTypes() {
        return new ResponseEntity<>(TResponse.<List<PaymentTypeDTO>>tResponseBuilder()
                .response(this.paymentTypeService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isActive")
    public ResponseEntity<TResponse<List<PaymentTypeDTO>>> getAllByActiveState(
            @RequestParam(value = "isActive", required = false) boolean isActive) {

        return new ResponseEntity<>(TResponse.<List<PaymentTypeDTO>>tResponseBuilder()
                .response(this.paymentTypeService.getAllByActiveState(isActive))
                .build(), HttpStatus.OK
        );
    }

}
