package src.controller.rental;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.rental.requests.CreateRentalRequest;
import src.controller.rental.requests.ReturnRentalRequest;
import src.controller.rental.requests.ShowRentalRequest;
import src.controller.rental.requests.UpdateRentalRequest;
import src.controller.rental.responses.RentalResponse;
import src.controller.rental.responses.RentalStatusResponse;
import src.controller.rental.responses.ShowRentalResponse;
import src.service.rental.RentalService;
import src.service.rental.status.RentalStatusService;

import java.util.List;

import static src.controller.rental.LogConstant.*;

@RestController
@RequestMapping("api/v1/rentals")
@RequiredArgsConstructor
@Validated
public class RentalsController {
    private static final Logger logger = LoggerFactory.getLogger(RentalsController.class);
    private final RentalService rentalService;
    private final RentalStatusService rentalStatusService;

    @PostMapping("/showRental")
    public ResponseEntity<TResponse<ShowRentalResponse>> showRental(@Valid @RequestBody ShowRentalRequest showRentalRequest) {
        logger.info(SHOWING_RENTAL_DETAILS, showRentalRequest.toString());
        ShowRentalResponse response = this.rentalService.showRentalDetails(showRentalRequest);
        logger.info(RENTAL_DETAILS_SHOWN);
        return new ResponseEntity<>(TResponse.<ShowRentalResponse>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Void> createRental(
            @Valid @RequestBody CreateRentalRequest createRentalRequest) {
        logger.info(CREATING_NEW_RENTAL, createRentalRequest.toString());
        rentalService.create(createRentalRequest);
        logger.info(RENTAL_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping
    public ResponseEntity<TResponse<RentalResponse>> updateRental(@RequestBody UpdateRentalRequest updateRentalRequest) {
        logger.info(UPDATING_RENTAL, updateRentalRequest.toString());
        RentalResponse response = this.rentalService.update(updateRentalRequest);
        logger.info(RENTAL_UPDATED, response.toString());
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @PutMapping("/startRental/{rentalId}")
    public ResponseEntity<TResponse<RentalResponse>> startRental(@PathVariable int rentalId) {
        logger.info(STARTING_RENTAL, rentalId);
        RentalResponse response = this.rentalService.startRental(rentalId);
        logger.info(RENTAL_STARTED, response.toString());
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @PutMapping("/returnRental")
    public ResponseEntity<TResponse<?>> returnRental(@RequestBody ReturnRentalRequest returnRentalRequest) {
        logger.info(RETURNING_RENTAL, returnRentalRequest.toString());
        this.rentalService.returnCar(returnRentalRequest);
        logger.info(RENTAL_RETURNED);
        return new ResponseEntity<>(TResponse.tResponseBuilder().build(), HttpStatus.OK);
    }

    @PutMapping("/cancelRental/{rentalId}")
    public ResponseEntity<TResponse<RentalResponse>> cancelRental(@PathVariable int rentalId) {
        logger.info(CANCELING_RENTAL, rentalId);
        RentalResponse response = this.rentalService.cancelRental(rentalId);
        logger.info(RENTAL_CANCELED, response.toString());
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllRentals() {
        logger.info(GETTING_ALL_RENTALS);
        List<RentalResponse> rentals = this.rentalService.getAll();
        logger.info(RETRIEVED_ALL_RENTALS, rentals.size());
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(rentals)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/statuses")
    public ResponseEntity<TResponse<List<RentalStatusResponse>>> getAllRentalStatuses() {
        logger.info(GETTING_ALL_RENTAL_STATUSES);
        List<RentalStatusResponse> statuses = this.rentalStatusService.getAll();
        logger.info(RETRIEVED_ALL_RENTAL_STATUSES, statuses.size());
        return new ResponseEntity<>(
                TResponse.<List<RentalStatusResponse>>tResponseBuilder().response(statuses).build()
                , HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        logger.info(GETTING_RENTAL_COUNT_BY_DELETED_STATE, isDeleted);
        int count = this.rentalService.getCountByDeletedState(isDeleted);
        logger.info(RETRIEVED_RENTAL_COUNT_BY_DELETED_STATE, count);
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(count)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/countByStatus/{status}")
    public ResponseEntity<TResponse<Integer>> getCountByStatus(@PathVariable int status) {
        logger.info(GETTING_RENTAL_COUNT_BY_STATUS, status);
        int count = this.rentalService.getCountByStatusId(status);
        logger.info(RETRIEVED_RENTAL_COUNT_BY_STATUS, count);
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(count)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<RentalResponse>> getRentalById(@PathVariable int id) {
        logger.info(GETTING_RENTAL_BY_ID, id);
        RentalResponse rental = this.rentalService.getById(id);
        logger.info(RETRIEVED_RENTAL_BY_ID, id);
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(rental).build(), HttpStatus.OK
        );
    }


    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        logger.info(GETTING_RENTALS_BY_DELETED_STATE, isDeleted);
        List<RentalResponse> rentals = this.rentalService.getAllByDeletedState(isDeleted);
        logger.info(RETRIEVED_RENTALS_BY_DELETED_STATE, rentals.size());
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(rentals)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "statusId")
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllByStatus(
            @RequestParam(value = "statusId", required = false) Integer statusId) {
        logger.info(GETTING_RENTALS_BY_STATUS, statusId);
        List<RentalResponse> rentals = this.rentalService.getAllByStatus(statusId);
        logger.info(RETRIEVED_RENTALS_BY_STATUS, rentals.size());
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(rentals)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        logger.info(DELETING_RENTAL, id, isHardDelete);
        this.rentalService.delete(id, isHardDelete);
        logger.info(RENTAL_DELETED_SUCCESSFULLY, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
