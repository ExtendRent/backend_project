package source_files.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import source_files.controllers.paperWorkControllers.DiscountsController;
import source_files.data.DTO.paperWorkDTOs.DiscountDTO;
import source_files.data.requests.paperworkRequests.discountRequests.CreateDiscountRequest;
import source_files.data.requests.paperworkRequests.discountRequests.UpdateDiscountRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.DiscountService;

public class DiscountControllerTest {

    private final DiscountService discountService = mock(DiscountService.class);
    private final DiscountsController discountController = new DiscountsController(discountService);

    @Test
    public void testCreateDiscountCode() {
        // Given
        CreateDiscountRequest createDiscountRequest = new CreateDiscountRequest();

        // When
        ResponseEntity<Void> responseEntity = discountController.createDiscountCode(createDiscountRequest);

        // Then
        verify(discountService, times(1)).create(createDiscountRequest);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateDiscountCode() {
        // Given
        UpdateDiscountRequest updateDiscountRequest = new UpdateDiscountRequest();

        // When
        ResponseEntity<TResponse<DiscountDTO>> responseEntity = discountController.updateDiscountCode(updateDiscountRequest);

        // Then
        verify(discountService, times(1)).update(updateDiscountRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    // Write similar test methods for the remaining controller methods
}

