package src.controllers.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controllers.paperwork.responses.RentalResponse;
import src.controllers.user.requests.customer.CreateCustomerRequest;
import src.controllers.user.requests.customer.UpdateCustomerRequest;
import src.controllers.user.responses.CustomerResponse;
import src.data.global_responses.TResponse;
import src.services.user.customer.CustomerService;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
@Validated
@CrossOrigin
public class CustomersController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {
        this.customerService.create(createCustomerRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CustomerResponse>> updateCustomer(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {
        return new ResponseEntity<>(TResponse.<CustomerResponse>tResponseBuilder()
                .response(this.customerService.update(updateCustomerRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CustomerResponse>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<CustomerResponse>>tResponseBuilder()
                .response(this.customerService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(this.customerService.getCountByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/countByStatus/{status}")
    public ResponseEntity<TResponse<Integer>> getCountByStatus(@PathVariable String status) {
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(this.customerService.getCountByStatus(status))
                .build(), HttpStatus.OK
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CustomerResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CustomerResponse>tResponseBuilder()
                .response(this.customerService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/rentals/{customerId}")
    public ResponseEntity<TResponse<List<RentalResponse>>> getRentalHistory(@PathVariable int customerId) {
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(this.customerService.getRentalHistory(customerId))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CustomerResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CustomerResponse>>tResponseBuilder()
                .response(this.customerService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.customerService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
