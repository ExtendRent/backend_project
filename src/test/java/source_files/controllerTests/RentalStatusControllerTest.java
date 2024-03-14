package source_files.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import source_files.controllers.paperWork.RentalStatusesController;
import source_files.controllers.paperWork.dtos.RentalStatusDTO;
import source_files.data.responses.TResponse;
import source_files.services.paperWork.abstracts.RentalStatusService;

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
        List<RentalStatusDTO> rentalStatusList = new ArrayList<>();
        rentalStatusList.add(new RentalStatusDTO(1, "Active", true));
        rentalStatusList.add(new RentalStatusDTO(2, "Inactive", false));

        // Mock the behavior of the rentalStatusService
        when(rentalStatusService.getAll()).thenReturn(rentalStatusList);

        // When
        ResponseEntity<TResponse<List<RentalStatusDTO>>> responseEntity = rentalStatusController.getAll();

        // Then
        verify(rentalStatusService, times(1)).getAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rentalStatusList, Objects.requireNonNull(responseEntity.getBody()).response());
    }
}
