package com.extendrent.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import src.controller.discount.DiscountController;
import src.controller.discount.response.DiscountResponse;
import src.controller.discount.request.CreateDiscountRequest;
import src.controller.discount.request.UpdateDiscountRequest;
import src.controller.TResponse;
import src.service.discount.DiscountService;

public class DiscountControllerTest {

    private final DiscountService discountService = mock(DiscountService.class);
    private final DiscountController discountController = new DiscountController(discountService);

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
        ResponseEntity<TResponse<DiscountResponse>> responseEntity = discountController.updateDiscountCode(updateDiscountRequest);

        // Then
        verify(discountService, times(1)).update(updateDiscountRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    // Write similar test methods for the remaining controller methods
}

