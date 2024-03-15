package source_files.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import source_files.controllers.paperWork.PaymentDetailsController;
import source_files.controllers.paperWork.dtos.PaymentDetailsDTO;
import source_files.controllers.paperWork.requests.payment.UpdatePaymentDetailsRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWork.abstracts.PaymentDetailsService;

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
        ResponseEntity<TResponse<PaymentDetailsDTO>> responseEntity = paymentDetailsController.update(updatePaymentDetailsRequest);

        // Then
        verify(paymentDetailsService, times(1)).update(updatePaymentDetailsRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetPaymentDetailsById() {
        // Given
        int id = 1;

        // When
        ResponseEntity<TResponse<PaymentDetailsDTO>> responseEntity = paymentDetailsController.getById(id);

        // Then
        verify(paymentDetailsService, times(1)).getById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
