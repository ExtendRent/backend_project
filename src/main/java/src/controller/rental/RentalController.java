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
import src.core.rest.BaseController;
import src.service.rental.RentalService;
import src.service.rental.status.RentalStatusService;

import java.util.List;

import static src.controller.rental.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/rentals")
@RequiredArgsConstructor
public class RentalController extends BaseController {
    private final RentalService rentalService;
    private final RentalStatusService rentalStatusService;

    @PostMapping("/showRental")
    public ResponseEntity<TResponse<ShowRentalResponse>> showRental(@Valid @RequestBody ShowRentalRequest showRentalRequest) {
        log.info(SHOWING_RENTAL_DETAILS, showRentalRequest.toString());
        ShowRentalResponse response = rentalService.showRentalDetails(showRentalRequest);
        log.info(RENTAL_DETAILS_SHOWN);
        return answer(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createRental(
            @Valid @RequestBody CreateRentalRequest createRentalRequest) {
        log.info(CREATING_NEW_RENTAL, createRentalRequest.toString());
        rentalService.create(createRentalRequest);
        log.info(RENTAL_SUCCESSFULLY_CREATED);
        return answer(HttpStatus.NO_CONTENT);
    }


    @PutMapping
    public ResponseEntity<TResponse<RentalResponse>> updateRental(@RequestBody UpdateRentalRequest updateRentalRequest) {
        log.info(UPDATING_RENTAL, updateRentalRequest.toString());
        RentalResponse response = rentalService.update(updateRentalRequest);
        log.info(RENTAL_UPDATED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @PutMapping("/startRental/{rentalId}")
    public ResponseEntity<TResponse<RentalResponse>> startRental(@PathVariable int rentalId) {
        log.info(STARTING_RENTAL, rentalId);
        RentalResponse response = rentalService.startRental(rentalId);
        log.info(RENTAL_STARTED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @PutMapping("/returnRental")
    public ResponseEntity<TResponse<RentalResponse>> returnRental(@RequestBody ReturnRentalRequest returnRentalRequest) {
        log.info(RETURNING_RENTAL, returnRentalRequest.toString());
        RentalResponse response = rentalService.returnCar(returnRentalRequest);
        log.info(RENTAL_RETURNED);
        return answer(response, HttpStatus.OK);
    }

    @PutMapping("/cancelRental/{rentalId}")
    public ResponseEntity<TResponse<RentalResponse>> cancelRental(@PathVariable int rentalId) {
        log.info(CANCELING_RENTAL, rentalId);
        RentalResponse response = rentalService.cancelRental(rentalId);
        log.info(RENTAL_CANCELED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllRentals() {
        log.info(GETTING_ALL_RENTALS);
        List<RentalResponse> response = rentalService.getAll();
        log.info(RETRIEVED_ALL_RENTALS, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/statuses")
    public ResponseEntity<TResponse<List<RentalStatusResponse>>> getAllRentalStatuses() {
        log.info(GETTING_ALL_RENTAL_STATUSES);
        List<RentalStatusResponse> response = rentalStatusService.getAll();
        log.info(RETRIEVED_ALL_RENTAL_STATUSES, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        log.info(GETTING_RENTAL_COUNT_BY_DELETED_STATE, isDeleted);
        int response = rentalService.getCountByDeletedState(isDeleted);
        log.info(RETRIEVED_RENTAL_COUNT_BY_DELETED_STATE, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/countByStatus/{status}")
    public ResponseEntity<TResponse<Integer>> getCountByStatus(@PathVariable int status) {
        log.info(GETTING_RENTAL_COUNT_BY_STATUS, status);
        int response = rentalService.getCountByStatusId(status);
        log.info(RETRIEVED_RENTAL_COUNT_BY_STATUS, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<RentalResponse>> getRentalById(@PathVariable int id) {
        log.info(GETTING_RENTAL_BY_ID, id);
        RentalResponse response = rentalService.getById(id);
        log.info(RETRIEVED_RENTAL_BY_ID, id);
        return answer(response, HttpStatus.OK);
    }


    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(GETTING_RENTALS_BY_DELETED_STATE, isDeleted);
        List<RentalResponse> response = rentalService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_RENTALS_BY_DELETED_STATE, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = "statusId")
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllByStatus(
            @RequestParam(value = "statusId", required = false) Integer statusId) {
        log.info(GETTING_RENTALS_BY_STATUS, statusId);
        List<RentalResponse> response = rentalService.getAllByStatus(statusId);
        log.info(RETRIEVED_RENTALS_BY_STATUS, response.size());
        return answer(response, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_RENTAL, id, isHardDelete);
        rentalService.delete(id, isHardDelete);
        log.info(RENTAL_DELETED_SUCCESSFULLY, id);
        return answer(HttpStatus.NO_CONTENT);
    }
}
