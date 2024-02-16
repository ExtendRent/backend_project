package source_files.controllers.userControllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.requests.userRequests.CreateCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.CustomerService;

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

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CustomerDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CustomerDTO>tResponseBuilder()
                .response(this.customerService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/rentals/rentalHistory{customerId}")
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
