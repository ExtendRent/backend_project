package src.controller.rental;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.rental.request.CreateRentalRequest;
import src.controller.rental.request.ReturnRentalRequest;
import src.controller.rental.request.ShowRentalRequest;
import src.controller.rental.request.UpdateRentalRequest;
import src.controller.rental.response.RentalResponse;
import src.controller.rental.response.RentalStatusResponse;
import src.controller.rental.response.ShowRentalResponse;
import src.service.rental.RentalService;
import src.service.rental.status.RentalStatusService;

import java.util.List;

import static src.controller.rental.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final RentalStatusService rentalStatusService;

    @PostMapping("/showRental")
    public ResponseEntity<TResponse<ShowRentalResponse>> showRental(@Valid @RequestBody ShowRentalRequest showRentalRequest) {
        log.info(SHOWING_RENTAL_DETAILS, showRentalRequest.toString());
        ShowRentalResponse response = this.rentalService.showRentalDetails(showRentalRequest);
        log.info(RENTAL_DETAILS_SHOWN);
        return new ResponseEntity<>(TResponse.<ShowRentalResponse>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Void> createRental(
            @Valid @RequestBody CreateRentalRequest createRentalRequest) {
        log.info(CREATING_NEW_RENTAL, createRentalRequest.toString());
        rentalService.create(createRentalRequest);
        log.info(RENTAL_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping
    public ResponseEntity<TResponse<RentalResponse>> updateRental(@RequestBody UpdateRentalRequest updateRentalRequest) {
        log.info(UPDATING_RENTAL, updateRentalRequest.toString());
        RentalResponse response = this.rentalService.update(updateRentalRequest);
        log.info(RENTAL_UPDATED, response.toString());
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @PutMapping("/startRental/{rentalId}")
    public ResponseEntity<TResponse<RentalResponse>> startRental(@PathVariable int rentalId) {
        log.info(STARTING_RENTAL, rentalId);
        RentalResponse response = this.rentalService.startRental(rentalId);
        log.info(RENTAL_STARTED, response.toString());
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @PutMapping("/returnRental")
    public ResponseEntity<TResponse<?>> returnRental(@RequestBody ReturnRentalRequest returnRentalRequest) {
        log.info(RETURNING_RENTAL, returnRentalRequest.toString());
        this.rentalService.returnCar(returnRentalRequest);
        log.info(RENTAL_RETURNED);
        return new ResponseEntity<>(TResponse.tResponseBuilder().build(), HttpStatus.OK);
    }

    @PutMapping("/cancelRental/{rentalId}")
    public ResponseEntity<TResponse<RentalResponse>> cancelRental(@PathVariable int rentalId) {
        log.info(CANCELING_RENTAL, rentalId);
        RentalResponse response = this.rentalService.cancelRental(rentalId);
        log.info(RENTAL_CANCELED, response.toString());
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllRentals() {
        log.info(GETTING_ALL_RENTALS);
        List<RentalResponse> rentals = this.rentalService.getAll();
        log.info(RETRIEVED_ALL_RENTALS, rentals.size());
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(rentals)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/statuses")
    public ResponseEntity<TResponse<List<RentalStatusResponse>>> getAllRentalStatuses() {
        log.info(GETTING_ALL_RENTAL_STATUSES);
        List<RentalStatusResponse> statuses = this.rentalStatusService.getAll();
        log.info(RETRIEVED_ALL_RENTAL_STATUSES, statuses.size());
        return new ResponseEntity<>(
                TResponse.<List<RentalStatusResponse>>tResponseBuilder().response(statuses).build()
                , HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        log.info(GETTING_RENTAL_COUNT_BY_DELETED_STATE, isDeleted);
        int count = this.rentalService.getCountByDeletedState(isDeleted);
        log.info(RETRIEVED_RENTAL_COUNT_BY_DELETED_STATE, count);
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(count)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/countByStatus/{status}")
    public ResponseEntity<TResponse<Integer>> getCountByStatus(@PathVariable int status) {
        log.info(GETTING_RENTAL_COUNT_BY_STATUS, status);
        int count = this.rentalService.getCountByStatusId(status);
        log.info(RETRIEVED_RENTAL_COUNT_BY_STATUS, count);
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(count)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<RentalResponse>> getRentalById(@PathVariable int id) {
        log.info(GETTING_RENTAL_BY_ID, id);
        RentalResponse rental = this.rentalService.getById(id);
        log.info(RETRIEVED_RENTAL_BY_ID, id);
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(rental).build(), HttpStatus.OK
        );
    }


    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(GETTING_RENTALS_BY_DELETED_STATE, isDeleted);
        List<RentalResponse> rentals = this.rentalService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_RENTALS_BY_DELETED_STATE, rentals.size());
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(rentals)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "statusId")
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllByStatus(
            @RequestParam(value = "statusId", required = false) Integer statusId) {
        log.info(GETTING_RENTALS_BY_STATUS, statusId);
        List<RentalResponse> rentals = this.rentalService.getAllByStatus(statusId);
        log.info(RETRIEVED_RENTALS_BY_STATUS, rentals.size());
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(rentals)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_RENTAL, id, isHardDelete);
        this.rentalService.delete(id, isHardDelete);
        log.info(RENTAL_DELETED_SUCCESSFULLY, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
