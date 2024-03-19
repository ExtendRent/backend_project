package com.extendrent.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import src.controller.rental.RentalController;
import src.controller.rental.response.RentalResponse;
import src.controller.rental.response.RentalStatusResponse;
import src.controller.rental.response.ShowRentalResponse;
import src.controller.rental.request.CreateRentalRequest;
import src.controller.rental.request.ReturnRentalRequest;
import src.controller.rental.request.ShowRentalRequest;
import src.controller.rental.request.UpdateRentalRequest;
import src.controller.TResponse;
import src.service.rental.RentalService;
import src.service.rental.status.RentalStatusService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RentalControllerTest {

    private final RentalService rentalService = mock(RentalService.class);
    private final RentalStatusService rentalStatusService = mock(RentalStatusService.class);
    private final RentalController rentalController = new RentalController(rentalService, rentalStatusService);

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
        RentalResponse updatedRentalResponse = RentalResponse.builder().build();

        // Mock the behavior of the rentalService
        when(rentalService.update(updateRentalRequest)).thenReturn(updatedRentalResponse);

        // When
        ResponseEntity<TResponse<RentalResponse>> responseEntity = rentalController.updateRental(updateRentalRequest);

        // Then
        verify(rentalService, times(1)).update(updateRentalRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedRentalResponse, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testGetAllRentals() {
        // Given
        List<RentalResponse> rentalList = new ArrayList<>();

        when(rentalService.getAll()).thenReturn(rentalList);

        // When
        ResponseEntity<TResponse<List<RentalResponse>>> responseEntity = rentalController.getAllRentals();

        // Then
        verify(rentalService, times(1)).getAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllStatuses() {
        // Given
        List<RentalStatusResponse> rentalStatusList = new ArrayList<>();
        rentalStatusList.add(new RentalStatusResponse(1, "Active", true));
        rentalStatusList.add(new RentalStatusResponse(2, "Inactive", false));

        // Mock the behavior of the rentalStatusService
        when(rentalStatusService.getAll()).thenReturn(rentalStatusList);

        // When
        ResponseEntity<TResponse<List<RentalStatusResponse>>> responseEntity = rentalController.getAllRentalStatuses();

        // Then
        verify(rentalStatusService, times(1)).getAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rentalStatusList, Objects.requireNonNull(responseEntity.getBody()).response());
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
        RentalResponse rentalResponse = RentalResponse.builder().build();

        // Mock the behavior of the rentalService
        when(rentalService.startRental(rentalId)).thenReturn(rentalResponse);

        // When
        ResponseEntity<TResponse<RentalResponse>> responseEntity = rentalController.startRental(rentalId);

        // Then
        verify(rentalService, times(1)).startRental(rentalId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rentalResponse, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testGetRentalById() {
        // Given
        int id = 1;
        RentalResponse rentalResponse = RentalResponse.builder().build();

        // Mock the behavior of the rentalService
        when(rentalService.getById(id)).thenReturn(rentalResponse);

        // When
        ResponseEntity<TResponse<RentalResponse>> responseEntity = rentalController.getRentalById(id);

        // Then
        verify(rentalService, times(1)).getById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rentalResponse, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testReturnRental() {
        // Given
        ReturnRentalRequest returnRentalRequest = new ReturnRentalRequest();

        // Mock the behavior of the rentalService
        when(rentalService.returnCar(returnRentalRequest)).thenReturn(RentalResponse.builder().build());

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
        List<RentalResponse> rentalList = new ArrayList<>();
        rentalList.add(RentalResponse.builder().build());

        // Mock the behavior of the rentalService
        when(rentalService.getAllByDeletedState(isDeleted)).thenReturn(rentalList);

        // When
        ResponseEntity<TResponse<List<RentalResponse>>> responseEntity = rentalController.getAllByDeletedState(isDeleted);

        // Then
        verify(rentalService, times(1)).getAllByDeletedState(isDeleted);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rentalList, Objects.requireNonNull(responseEntity.getBody()).response());
    }


    @Test
    public void testGetAllByStatus() {
        // Given
        int statusId = 1;
        List<RentalResponse> rentalList = new ArrayList<>();
        rentalList.add(RentalResponse.builder().build());

        // Mock the behavior of the rentalService
        when(rentalService.getAllByStatus(statusId)).thenReturn(rentalList);

        // When
        ResponseEntity<TResponse<List<RentalResponse>>> responseEntity = rentalController.getAllByDeletedState(false);

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

