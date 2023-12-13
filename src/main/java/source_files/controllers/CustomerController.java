package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        return ResponseEntity.ok(TResponse.tResponseBuilder() //TODO Builder okunmuyor. çözülecek.
                .isSuccess(true)
                .response(this.customerService.add(addCustomerRequest))
                .message("Müşteri eklendi")
                .build()
        );
    }
}
