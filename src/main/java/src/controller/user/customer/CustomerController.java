package src.controller.user.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.rental.response.RentalResponse;
import src.controller.user.customer.request.CreateCustomerRequest;
import src.controller.user.customer.request.UpdateCustomerRequest;
import src.controller.user.customer.response.CustomerResponse;
import src.service.user.customer.CustomerService;

import java.util.List;

import static src.controller.user.customer.LogConstant.*;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor

public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {
        logger.info(CREATING_NEW_CUSTOMER, createCustomerRequest.toString());
        this.customerService.create(createCustomerRequest);
        logger.info(CUSTOMER_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CustomerResponse>> updateCustomer(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {
        logger.info(UPDATING_CUSTOMER, updateCustomerRequest.toString());
        CustomerResponse updatedCustomer = this.customerService.update(updateCustomerRequest);
        logger.info(CUSTOMER_UPDATED, updatedCustomer.toString());
        return new ResponseEntity<>(TResponse.<CustomerResponse>tResponseBuilder()
                .response(updatedCustomer)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CustomerResponse>>> getAll() {
        logger.info(GETTING_ALL_CUSTOMERS);
        List<CustomerResponse> customers = this.customerService.getAll();
        logger.info(RETRIEVED_ALL_CUSTOMERS, customers.size());
        return new ResponseEntity<>(TResponse.<List<CustomerResponse>>tResponseBuilder()
                .response(customers)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        logger.info(GETTING_CUSTOMER_COUNT_BY_DELETED_STATE, isDeleted);
        int count = this.customerService.getCountByDeletedState(isDeleted);
        logger.info(RETRIEVED_CUSTOMER_COUNT_BY_DELETED_STATE, count);
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(count)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/countByStatus/{status}")
    public ResponseEntity<TResponse<Integer>> getCountByStatus(@PathVariable String status) {
        logger.info(GETTING_CUSTOMER_COUNT_BY_STATUS, status);
        int count = this.customerService.getCountByStatus(status);
        logger.info(RETRIEVED_CUSTOMER_COUNT_BY_STATUS, count);
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(count)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CustomerResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_CUSTOMER_BY_ID, id);
        CustomerResponse customer = this.customerService.getById(id);
        logger.info(RETRIEVED_CUSTOMER_BY_ID, id);
        return new ResponseEntity<>(TResponse.<CustomerResponse>tResponseBuilder()
                .response(customer)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/rentals/{customerId}")
    public ResponseEntity<TResponse<List<RentalResponse>>> getRentalHistory(@PathVariable int customerId) {
        logger.info(GETTING_RENTAL_HISTORY, customerId);
        List<RentalResponse> rentalHistory = this.customerService.getRentalHistory(customerId);
        logger.info(RETRIEVED_RENTAL_HISTORY, customerId, rentalHistory.size());
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(rentalHistory)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CustomerResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        logger.info(GETTING_CUSTOMERS_BY_DELETED_STATE, isDeleted);
        List<CustomerResponse> customers = this.customerService.getAllByDeletedState(isDeleted);
        logger.info(RETRIEVED_CUSTOMERS_BY_DELETED_STATE, customers.size());
        return new ResponseEntity<>(TResponse.<List<CustomerResponse>>tResponseBuilder()
                .response(customers)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        logger.info(DELETING_CUSTOMER_WITH_ID, id, isHardDelete);
        this.customerService.delete(id, isHardDelete);
        logger.info(CUSTOMER_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
