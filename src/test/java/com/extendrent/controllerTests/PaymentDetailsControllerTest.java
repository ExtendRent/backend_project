package com.extendrent.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import src.controller.payment.PaymentDetailsController;
import src.controller.payment.responses.PaymentDetailsResponse;
import src.controller.payment.requests.UpdatePaymentDetailsRequest;
import src.controller.TResponse;
import src.service.payment.detail.PaymentDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentDetailsControllerTest {

    private final PaymentDetailsService paymentDetailsService = mock(PaymentDetailsService.class);
    private final PaymentDetailsController paymentDetailsController = new PaymentDetailsController(paymentDetailsService);

    @Test
    public void testUpdatePaymentDetails() {
        // Given
        UpdatePaymentDetailsRequest updatePaymentDetailsRequest = new UpdatePaymentDetailsRequest();

        // When
        ResponseEntity<TResponse<PaymentDetailsResponse>> responseEntity = paymentDetailsController.update(updatePaymentDetailsRequest);

        // Then
        verify(paymentDetailsService, times(1)).update(updatePaymentDetailsRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetPaymentDetailsById() {
        // Given
        int id = 1;

        // When
        ResponseEntity<TResponse<PaymentDetailsResponse>> responseEntity = paymentDetailsController.getById(id);

        // Then
        verify(paymentDetailsService, times(1)).getById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
