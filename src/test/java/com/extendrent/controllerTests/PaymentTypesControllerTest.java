package com.extendrent.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import src.controllers.paperwork.PaymentTypesController;
import src.controllers.paperwork.responses.PaymentTypeResponse;
import src.controllers.paperwork.requests.payment.UpdatePaymentTypeRequest;
import src.data.global_responses.TResponse;
import src.services.paperwork.payment_type.PaymentTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaymentTypesControllerTest {

    private final PaymentTypeService paymentTypeService = mock(PaymentTypeService.class);
    private final PaymentTypesController paymentTypeController = new PaymentTypesController(paymentTypeService);

    @Test
    public void testUpdatePaymentType() {
        // Given
        UpdatePaymentTypeRequest updatePaymentTypeRequest = new UpdatePaymentTypeRequest();

        // Mock the behavior of the paymentTypeService
        PaymentTypeResponse updatedPaymentTypeResponse = new PaymentTypeResponse();
        when(paymentTypeService.update(updatePaymentTypeRequest)).thenReturn(updatedPaymentTypeResponse);

        // When
        ResponseEntity<TResponse<PaymentTypeResponse>> responseEntity = paymentTypeController.updatePaymentType(updatePaymentTypeRequest);

        // Then
        verify(paymentTypeService, times(1)).update(updatePaymentTypeRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedPaymentTypeResponse, Objects.requireNonNull(responseEntity.getBody()).response());


    }
    @Test
    public void testGetAllPaymentTypes() {
        // Given
        List<PaymentTypeResponse> paymentTypeList = new ArrayList<>();
        paymentTypeList.add(new PaymentTypeResponse(1, "Credit Card", true));
        paymentTypeList.add(new PaymentTypeResponse(2, "Debit Card", false));

        // Mock the behavior of the paymentTypeService
        when(paymentTypeService.getAll()).thenReturn(paymentTypeList);

        // When
        ResponseEntity<TResponse<List<PaymentTypeResponse>>> responseEntity = paymentTypeController.GetAllPaymentTypes();

        // Then
        verify(paymentTypeService, times(1)).getAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(paymentTypeList, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testGetAllByActiveState() {
        // Given
        boolean isActive = true;
        List<PaymentTypeResponse> activePaymentTypes = new ArrayList<>();
        activePaymentTypes.add(new PaymentTypeResponse(1, "Credit Card", true));

        // Mock the behavior of the paymentTypeService
        when(paymentTypeService.getAllByActiveState(isActive)).thenReturn(activePaymentTypes);

        // When
        ResponseEntity<TResponse<List<PaymentTypeResponse>>> responseEntity = paymentTypeController.getAllByActiveState(isActive);

        // Then
        verify(paymentTypeService, times(1)).getAllByActiveState(isActive);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(activePaymentTypes, Objects.requireNonNull(responseEntity.getBody()).response());
    }
}
