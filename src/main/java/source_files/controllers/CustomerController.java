package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.CustomerService;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<HttpStatus> addCustomer(@RequestBody @Valid AddCustomerRequest addCustomerRequest) {
        this.customerService.add(addCustomerRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CustomerDTO>> updateCustomer(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {
        return ResponseEntity.ok(TResponse.<CustomerDTO>tResponseBuilder()
                .response(this.customerService.update(updateCustomerRequest))
                .message("Müşteri güncellendi.")
                .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<CustomerDTO>>> getAll() {
        return ResponseEntity.ok(TResponse.<List<CustomerDTO>>tResponseBuilder()
                .response(this.customerService.getAll())
                .message("Müşteri listesi getirildi.")
                .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<TResponse<CustomerDTO>> getById(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.<CustomerDTO>tResponseBuilder()
                .response(this.customerService.getById(id))
                .message("Müşteri getirildi.")
                .build()
        );
    }

    @GetMapping("{phoneNumber}")
    public ResponseEntity<TResponse<CustomerDTO>> getByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(TResponse.<CustomerDTO>tResponseBuilder()
                .response(this.customerService.getByPhoneNumber(phoneNumber))
                .message("Telefon Numarasına Göre Getirildi")
                .build()
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CustomerDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return ResponseEntity.ok(TResponse.<List<CustomerDTO>>tResponseBuilder()
                .response(this.customerService.getAllByDeletedState(isDeleted))
                .message("silinene göre müşteri Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<HttpStatus> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.customerService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
