package source_files.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import source_files.controllers.paperWork.RentalsController;
import source_files.controllers.paperWork.dtos.RentalDTO;
import source_files.controllers.paperWork.dtos.ShowRentalResponse;
import source_files.controllers.paperWork.requests.Rental.CreateRentalRequest;
import source_files.controllers.paperWork.requests.Rental.ReturnRentalRequest;
import source_files.controllers.paperWork.requests.Rental.ShowRentalRequest;
import source_files.controllers.paperWork.requests.Rental.UpdateRentalRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWork.abstracts.RentalService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RentalsControllerTest {

    private final RentalService rentalService = mock(RentalService.class);
    private final RentalsController rentalController = new RentalsController(rentalService);

    @Test
    public void testCreateRental() {
        // Given
        CreateRentalRequest createRentalRequest = new CreateRentalRequest();

        // When
        ResponseEntity<Void> responseEntity = rentalController.createRental(createRentalRequest);

        // Then
        verify(rentalService, times(1)).create(createRentalRequest);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testShowRental() {
        // Given
        ShowRentalRequest showRentalRequest = new ShowRentalRequest();

        // When
        ResponseEntity<TResponse<ShowRentalResponse>> responseEntity = rentalController.showRental(showRentalRequest);

        // Then
        verify(rentalService, times(1)).showRentalDetails(showRentalRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateRental() {
        // Given
        UpdateRentalRequest updateRentalRequest = new UpdateRentalRequest();
        RentalDTO updatedRentalDTO = RentalDTO.builder().build();

        // Mock the behavior of the rentalService
        when(rentalService.update(updateRentalRequest)).thenReturn(updatedRentalDTO);

        // When
        ResponseEntity<TResponse<RentalDTO>> responseEntity = rentalController.updateRental(updateRentalRequest);

        // Then
        verify(rentalService, times(1)).update(updateRentalRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedRentalDTO, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testGetAllRentals() {
        // Given
        List<RentalDTO> rentalList = new ArrayList<>();

        when(rentalService.getAll()).thenReturn(rentalList);

        // When
        ResponseEntity<TResponse<List<RentalDTO>>> responseEntity = rentalController.getAllRentals();

        // Then
        verify(rentalService, times(1)).getAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetCountByDeletedState() {
        // Given
        boolean isDeleted = true;
        int count = 10;

        // Mock the behavior of the rentalService
        when(rentalService.getCountByDeletedState(isDeleted)).thenReturn(count);

        // When
        ResponseEntity<TResponse<Integer>> responseEntity = rentalController.getCountByDeletedState(isDeleted);

        // Then
        verify(rentalService, times(1)).getCountByDeletedState(isDeleted);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(count, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testGetCountByStatus() {
        // Given
        int status = 1;
        int count = 5;

        // Mock the behavior of the rentalService
        when(rentalService.getCountByStatusId(status)).thenReturn(count);

        // When
        ResponseEntity<TResponse<Integer>> responseEntity = rentalController.getCountByStatus(status);

        // Then
        verify(rentalService, times(1)).getCountByStatusId(status);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(count, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testStartRental() {
        // Given
        int rentalId = 1;
        RentalDTO rentalDTO = RentalDTO.builder().build();

        // Mock the behavior of the rentalService
        when(rentalService.startRental(rentalId)).thenReturn(rentalDTO);

        // When
        ResponseEntity<TResponse<RentalDTO>> responseEntity = rentalController.startRental(rentalId);

        // Then
        verify(rentalService, times(1)).startRental(rentalId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rentalDTO, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testGetRentalById() {
        // Given
        int id = 1;
        RentalDTO rentalDTO = RentalDTO.builder().build();

        // Mock the behavior of the rentalService
        when(rentalService.getById(id)).thenReturn(rentalDTO);

        // When
        ResponseEntity<TResponse<RentalDTO>> responseEntity = rentalController.getRentalById(id);

        // Then
        verify(rentalService, times(1)).getById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rentalDTO, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testReturnRental() {
        // Given
        ReturnRentalRequest returnRentalRequest = new ReturnRentalRequest();

        // Mock the behavior of the rentalService
        when(rentalService.returnCar(returnRentalRequest)).thenReturn(RentalDTO.builder().build());

        // When
        ResponseEntity<TResponse<?>> responseEntity = rentalController.returnRental(returnRentalRequest);

        // Then
        verify(rentalService, times(1)).returnCar(returnRentalRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllByDeletedState() {
        // Given
        boolean isDeleted = true;
        List<RentalDTO> rentalList = new ArrayList<>();
        rentalList.add(RentalDTO.builder().build());

        // Mock the behavior of the rentalService
        when(rentalService.getAllByDeletedState(isDeleted)).thenReturn(rentalList);

        // When
        ResponseEntity<TResponse<List<RentalDTO>>> responseEntity = rentalController.getAllByDeletedState(isDeleted);

        // Then
        verify(rentalService, times(1)).getAllByDeletedState(isDeleted);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rentalList, Objects.requireNonNull(responseEntity.getBody()).response());
    }


    @Test
    public void testGetAllByStatus() {
        // Given
        int statusId = 1;
        List<RentalDTO> rentalList = new ArrayList<>();
        rentalList.add(RentalDTO.builder().build());

        // Mock the behavior of the rentalService
        when(rentalService.getAllByStatus(statusId)).thenReturn(rentalList);

        // When
        ResponseEntity<TResponse<List<RentalDTO>>> responseEntity = rentalController.getAllByDeletedState(statusId);

        // Then
        verify(rentalService, times(1)).getAllByStatus(statusId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rentalList, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testDelete() {
        // Given
        int id = 1;
        boolean isHardDelete = true;

        // When
        ResponseEntity<Void> responseEntity = rentalController.delete(id, isHardDelete);

        // Then
        verify(rentalService, times(1)).delete(id, isHardDelete);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

}

