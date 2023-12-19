package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.CustomerService;

@RestController
@RequestMapping("api/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/add/customer")
    public ResponseEntity<TResponse<?>> addCustomer(@RequestBody AddCustomerRequest addCustomerRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.customerService.add(addCustomerRequest))
                .message("Müşteri eklendi")
                .build()
        );
    }

    @GetMapping("{phoneNumber}")
    public ResponseEntity<TResponse<?>> getByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.customerService.getByPhoneNumber(phoneNumber))
                .message("Telefon Numarasına Göre Getirildi")
                .build()
        );
    }
}
