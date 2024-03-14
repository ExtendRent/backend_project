package source_files.controllers.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.controllers.paperWork.dtos.RentalDTO;
import source_files.controllers.user.dtos.CustomerDTO;
import source_files.controllers.user.requests.CreateCustomerRequest;
import source_files.controllers.user.requests.UpdateCustomerRequest;
import source_files.data.responses.TResponse;
import source_files.services.user.abstracts.CustomerService;

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
    public ResponseEntity<TResponse<CustomerDTO>> updateCustomer(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {
        return new ResponseEntity<>(TResponse.<CustomerDTO>tResponseBuilder()
                .response(this.customerService.update(updateCustomerRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CustomerDTO>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<CustomerDTO>>tResponseBuilder()
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
    public ResponseEntity<TResponse<CustomerDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CustomerDTO>tResponseBuilder()
                .response(this.customerService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/rentals/{customerId}")
    public ResponseEntity<TResponse<List<RentalDTO>>> getRentalHistory(@PathVariable int customerId) {
        return new ResponseEntity<>(TResponse.<List<RentalDTO>>tResponseBuilder()
                .response(this.customerService.getRentalHistory(customerId))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CustomerDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CustomerDTO>>tResponseBuilder()
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
