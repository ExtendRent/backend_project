package source_files.servicesTests.rentalTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.requests.paperworkRequests.RentalRequests.CreateRentalRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.ShowRentalRequest;
import source_files.exception.ValidationException;
import source_files.services.BusinessRules.paperWork.RentalBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountEntityService;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.RentalEntityService;
import source_files.services.paperWorkServices.abstracts.PaymentService;
import source_files.services.paperWorkServices.rental.RentalManager;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.vehicleService.abstracts.CarService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RentalManagerTest {

    @Mock
    private RentalEntityService rentalEntityService;

    @Mock
    private ModelMapperService mapper;

    @Mock
    private CarService carService;

    @Mock
    private CustomerService customerService;

    @Mock
    private DiscountEntityService discountEntityService;

    @Mock
    private RentalBusinessRules rules;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private RentalManager rentalManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showRentalDetails_shouldThrowValidationException_whenCarNotAvailable() {
        // Arrange
        ShowRentalRequest showRentalRequest = new ShowRentalRequest();
        showRentalRequest.setCarEntityId(1);
        showRentalRequest.setStartDate(LocalDate.now());
        showRentalRequest.setEndDate(showRentalRequest.getStartDate().plusDays(1));

        when(carService.isCarAvailableBetween(1, showRentalRequest.getStartDate(), showRentalRequest.getEndDate()))
                .thenReturn(false);

        // Act & Assert
        assertThrows(ValidationException.class, () -> rentalManager.showRentalDetails(showRentalRequest));
    }

    @Test
    void showRentalDetails_shouldNotThrowException_whenCarIsAvailable() {
        // Arrange
        ShowRentalRequest showRentalRequest = new ShowRentalRequest();
        showRentalRequest.setCarEntityId(1);
        showRentalRequest.setStartDate(LocalDate.now());
        showRentalRequest.setEndDate(showRentalRequest.getStartDate().plusDays(1));

        when(carService.isCarAvailableBetween(1, showRentalRequest.getStartDate(), showRentalRequest.getEndDate()))
                .thenReturn(true);

        // Act & Assert
        assertDoesNotThrow(() -> rentalManager.showRentalDetails(showRentalRequest));
    }

    @Test
    void create_shouldCreateRentalEntityAndPay_whenDiscountCodeNotNull() {
        // Arrange
        CreateRentalRequest createRentalRequest = new CreateRentalRequest();
        createRentalRequest.setCarEntityId(1);
        createRentalRequest.setDiscountCode("DISCOUNT_CODE");

        RentalEntity rentalEntity = new RentalEntity();
        when(mapper.forRequest().map(any(), eq(RentalEntity.class))).thenReturn(rentalEntity);

        when(rules.discountCodeIsNotNull("DISCOUNT_CODE")).thenReturn(true);
        when(discountEntityService.getByDiscountCode("DISCOUNT_CODE")).thenReturn(new DiscountEntity());

        // Act
        rentalManager.create(createRentalRequest);

        // Assert
        verify(rentalEntityService).create(rentalEntity);
        verify(paymentService).pay(createRentalRequest, rentalEntity);
        verify(rentalEntityService).update(rentalEntity);
        verify(carService).addRental(1, rentalEntity);
        verify(customerService).addRental(anyInt(), eq(rentalEntity));
    }

    @Test
    void create_shouldHandleExceptionAndRollback_whenExceptionThrown() {
        // Arrange
        CreateRentalRequest createRentalRequest = new CreateRentalRequest();
        createRentalRequest.setCarEntityId(1);

        RentalEntity rentalEntity = new RentalEntity();
        when(mapper.forRequest().map(any(), eq(RentalEntity.class))).thenReturn(rentalEntity);

        when(rentalEntityService.update(rentalEntity)).thenThrow(new RuntimeException());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> rentalManager.create(createRentalRequest));

        // Assert
        verify(rentalEntityService).create(rentalEntity);
        verify(rentalEntityService).update(rentalEntity);
        verify(carService).removeRental(1, rentalEntity);
        verify(customerService).removeRental(anyInt(), eq(rentalEntity));
    }
}

