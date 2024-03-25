package src.controller.user.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.rental.response.RentalResponse;
import src.controller.user.customer.request.CreateCustomerRequest;
import src.controller.user.customer.request.UpdateCustomerRequest;
import src.controller.user.customer.response.CustomerResponse;
import src.core.rest.BaseController;
import src.service.user.customer.CustomerService;

import java.util.List;

import static src.controller.user.customer.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController extends BaseController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {
        log.info(CREATING_NEW_CUSTOMER, createCustomerRequest.toString());
        customerService.create(createCustomerRequest);
        log.info(CUSTOMER_SUCCESSFULLY_CREATED);
        return answer(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CustomerResponse>> updateCustomer(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {
        log.info(UPDATING_CUSTOMER, updateCustomerRequest.toString());
        CustomerResponse response = customerService.update(updateCustomerRequest);
        log.info(CUSTOMER_UPDATED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CustomerResponse>>> getAll() {
        log.info(GETTING_ALL_CUSTOMERS);
        List<CustomerResponse> response = customerService.getAll();
        log.info(RETRIEVED_ALL_CUSTOMERS, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        log.info(GETTING_CUSTOMER_COUNT_BY_DELETED_STATE, isDeleted);
        int response = customerService.getCountByDeletedState(isDeleted);
        log.info(RETRIEVED_CUSTOMER_COUNT_BY_DELETED_STATE, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/countByStatus/{status}")
    public ResponseEntity<TResponse<Integer>> getCountByStatus(@PathVariable String status) {
        log.info(GETTING_CUSTOMER_COUNT_BY_STATUS, status);
        int response = customerService.getCountByStatus(status);
        log.info(RETRIEVED_CUSTOMER_COUNT_BY_STATUS, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CustomerResponse>> getById(@PathVariable int id) {
        log.info(GETTING_CUSTOMER_BY_ID, id);
        CustomerResponse response = customerService.getById(id);
        log.info(RETRIEVED_CUSTOMER_BY_ID, id);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/rentals/{customerId}")
    public ResponseEntity<TResponse<List<RentalResponse>>> getRentalHistory(@PathVariable int customerId) {
        log.info(GETTING_RENTAL_HISTORY, customerId);
        List<RentalResponse> response = customerService.getRentalHistory(customerId);
        log.info(RETRIEVED_RENTAL_HISTORY, customerId, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CustomerResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(GETTING_CUSTOMERS_BY_DELETED_STATE, isDeleted);
        List<CustomerResponse> response = customerService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_CUSTOMERS_BY_DELETED_STATE, response.size());
        return answer(response, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_CUSTOMER_WITH_ID, id, isHardDelete);
        customerService.delete(id, isHardDelete);
        log.info(CUSTOMER_DELETED_SUCCESSFULLY_WITH_ID, id);
        return answer(HttpStatus.NO_CONTENT);
    }
}
