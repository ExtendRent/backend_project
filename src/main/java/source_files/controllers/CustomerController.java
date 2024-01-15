package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.CustomerService;

import java.util.List;

@RestController
@RequestMapping("api/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/add/customer")
    public ResponseEntity<TResponse<?>> addCustomer(@RequestBody @Valid AddCustomerRequest addCustomerRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.customerService.add(addCustomerRequest))
                .message("Müşteri eklendi.")
                .build()
        );
    }

    @PutMapping("/update/customer")
    public ResponseEntity<TResponse<?>> updateCustomer(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.customerService.update(updateCustomerRequest))
                .message("Müşteri güncellendi.")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAll() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.customerService.getAll())
                .message("Müşteri listesi getirildi.")
                .build()
        );
    }

    @GetMapping("/getById")
    public ResponseEntity<TResponse<?>> getById(@RequestParam int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.customerService.getById(id))
                .message("Müşteri getirildi.")
                .build()
        );
    }

    @GetMapping("/getPhoneNumber")
    public ResponseEntity<TResponse<?>> getByPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
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
                .message("Silinmeyen Araba Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {

        this.customerService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .message("Müşteri silindi.")
                .build()
        );
    }
}
