package src.controller.paperwork.rental;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.paperwork.rental.requests.CreateRentalRequest;
import src.controller.paperwork.rental.requests.ReturnRentalRequest;
import src.controller.paperwork.rental.requests.ShowRentalRequest;
import src.controller.paperwork.rental.requests.UpdateRentalRequest;
import src.controller.paperwork.rental.responses.RentalResponse;
import src.controller.paperwork.rental.responses.ShowRentalResponse;
import src.service.paperwork.rental.RentalService;

import java.util.List;

@RestController
@RequestMapping("api/v1/rentals")
@RequiredArgsConstructor
@Validated

public class RentalsController {
    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<Void> createRental(
            @Valid @RequestBody CreateRentalRequest createRentalRequest) {
        rentalService.create(createRentalRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/showRental")
    public ResponseEntity<TResponse<ShowRentalResponse>> showRental(@Valid @RequestBody ShowRentalRequest showRentalRequest) {
        return new ResponseEntity<>(TResponse.<ShowRentalResponse>tResponseBuilder()
                .response(this.rentalService.showRentalDetails(showRentalRequest))
                .build(), HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<TResponse<RentalResponse>> updateRental(@RequestBody UpdateRentalRequest updateRentalRequest) {
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(this.rentalService.update(updateRentalRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllRentals() {
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(this.rentalService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(this.rentalService.getCountByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/countByStatus/{status}")
    public ResponseEntity<TResponse<Integer>> getCountByStatus(@PathVariable int status) {
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(this.rentalService.getCountByStatusId(status))
                .build(), HttpStatus.OK
        );
    }

    @PutMapping("/startRental/{rentalId}")
    public ResponseEntity<TResponse<RentalResponse>> startRental(@PathVariable int rentalId) {
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(this.rentalService.startRental(rentalId))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<RentalResponse>> getRentalById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<RentalResponse>tResponseBuilder()
                .response(rentalService.getById(id)).build(), HttpStatus.OK
        );
    }

    @PutMapping("/returnRental")
    public ResponseEntity<TResponse<?>> returnRental(@RequestBody ReturnRentalRequest returnRentalRequest) {
        return new ResponseEntity<>(TResponse.tResponseBuilder()
                .response(this.rentalService.returnCar(returnRentalRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(this.rentalService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "statusId")
    public ResponseEntity<TResponse<List<RentalResponse>>> getAllByDeletedState(
            @RequestParam(value = "statusId", required = false) Integer statusId) {
        return new ResponseEntity<>(TResponse.<List<RentalResponse>>tResponseBuilder()
                .response(this.rentalService.getAllByStatus(statusId))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.rentalService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
