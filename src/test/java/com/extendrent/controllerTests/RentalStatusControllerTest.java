package com.extendrent.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import src.controller.paperwork.rental.RentalStatusesController;
import src.controller.paperwork.rental.responses.RentalStatusResponse;
import src.controller.TResponse;
import src.service.paperwork.rental.status.RentalStatusService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RentalStatusControllerTest {

    private final RentalStatusService rentalStatusService = mock(RentalStatusService.class);
    private final RentalStatusesController rentalStatusController = new RentalStatusesController(rentalStatusService);

    @Test
    public void testGetAll() {
        // Given
        List<RentalStatusResponse> rentalStatusList = new ArrayList<>();
        rentalStatusList.add(new RentalStatusResponse(1, "Active", true));
        rentalStatusList.add(new RentalStatusResponse(2, "Inactive", false));

        // Mock the behavior of the rentalStatusService
        when(rentalStatusService.getAll()).thenReturn(rentalStatusList);

        // When
        ResponseEntity<TResponse<List<RentalStatusResponse>>> responseEntity = rentalStatusController.getAll();

        // Then
        verify(rentalStatusService, times(1)).getAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rentalStatusList, Objects.requireNonNull(responseEntity.getBody()).response());
    }
}
