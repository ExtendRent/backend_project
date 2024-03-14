package source_files.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import source_files.controllers.paperWork.PaymentTypesController;
import source_files.controllers.paperWork.dtos.PaymentTypeDTO;
import source_files.controllers.paperWork.requests.paymentRequests.UpdatePaymentTypeRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWork.abstracts.PaymentTypeService;

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
        PaymentTypeDTO updatedPaymentTypeDTO = new PaymentTypeDTO();
        when(paymentTypeService.update(updatePaymentTypeRequest)).thenReturn(updatedPaymentTypeDTO);

        // When
        ResponseEntity<TResponse<PaymentTypeDTO>> responseEntity = paymentTypeController.updatePaymentType(updatePaymentTypeRequest);

        // Then
        verify(paymentTypeService, times(1)).update(updatePaymentTypeRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedPaymentTypeDTO, Objects.requireNonNull(responseEntity.getBody()).response());


    }
    @Test
    public void testGetAllPaymentTypes() {
        // Given
        List<PaymentTypeDTO> paymentTypeList = new ArrayList<>();
        paymentTypeList.add(new PaymentTypeDTO(1, "Credit Card", true));
        paymentTypeList.add(new PaymentTypeDTO(2, "Debit Card", false));

        // Mock the behavior of the paymentTypeService
        when(paymentTypeService.getAll()).thenReturn(paymentTypeList);

        // When
        ResponseEntity<TResponse<List<PaymentTypeDTO>>> responseEntity = paymentTypeController.GetAllPaymentTypes();

        // Then
        verify(paymentTypeService, times(1)).getAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(paymentTypeList, Objects.requireNonNull(responseEntity.getBody()).response());
    }

    @Test
    public void testGetAllByActiveState() {
        // Given
        boolean isActive = true;
        List<PaymentTypeDTO> activePaymentTypes = new ArrayList<>();
        activePaymentTypes.add(new PaymentTypeDTO(1, "Credit Card", true));

        // Mock the behavior of the paymentTypeService
        when(paymentTypeService.getAllByActiveState(isActive)).thenReturn(activePaymentTypes);

        // When
        ResponseEntity<TResponse<List<PaymentTypeDTO>>> responseEntity = paymentTypeController.getAllByActiveState(isActive);

        // Then
        verify(paymentTypeService, times(1)).getAllByActiveState(isActive);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(activePaymentTypes, Objects.requireNonNull(responseEntity.getBody()).response());
    }
}
