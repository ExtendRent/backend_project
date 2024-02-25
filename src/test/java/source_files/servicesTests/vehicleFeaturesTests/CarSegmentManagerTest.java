package source_files.servicesTests.vehicleFeaturesTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.CarSegmentDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarSegmentEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.CreateCarSegmentRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.UpdateCarSegmentRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarSegmentBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarSegmentEntityService;
import source_files.services.vehicleFeaturesServices.CarSegmentManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CarSegmentManagerTest {

    @InjectMocks
    private CarSegmentManager carSegmentManager;

    @Mock
    private ModelMapperService mapper;

    @Mock
    private CarSegmentEntityService entityService;

    @Mock
    private CarSegmentBusinessRules rules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldReturnMappedDTO_whenCreateCarSegmentRequestProvided() {
        // Arrange
        CreateCarSegmentRequest request = new CreateCarSegmentRequest();
        CarSegmentEntity entity = new CarSegmentEntity();
        CarSegmentDTO expectedDTO = new CarSegmentDTO();

        when(mapper.forRequest().map(request, CarSegmentEntity.class)).thenReturn(entity);
        when(entityService.create(entity)).thenReturn(entity);
        when(mapper.forResponse().map(entity, CarSegmentDTO.class)).thenReturn(expectedDTO);

        // Act
        CarSegmentDTO resultDTO = carSegmentManager.create(request);

        // Assert
        assertEquals(expectedDTO, resultDTO);
        verify(mapper.forRequest()).map(request, CarSegmentEntity.class);
        verify(entityService).create(entity);
        verify(mapper.forResponse()).map(entity, CarSegmentDTO.class);
    }

    @Test
    void update_shouldReturnMappedDTO_whenUpdateCarSegmentRequestProvided() {
        // Arrange
        UpdateCarSegmentRequest request = new UpdateCarSegmentRequest();
        CarSegmentEntity entity = new CarSegmentEntity();
        CarSegmentDTO expectedDTO = new CarSegmentDTO();

        when(mapper.forRequest().map(request, CarSegmentEntity.class)).thenReturn(entity);
        when(entityService.update(entity)).thenReturn(entity);
        when(mapper.forResponse().map(entity, CarSegmentDTO.class)).thenReturn(expectedDTO);

        // Act
        CarSegmentDTO resultDTO = carSegmentManager.update(request);

        // Assert
        assertEquals(expectedDTO, resultDTO);
        verify(mapper.forRequest()).map(request, CarSegmentEntity.class);
        verify(entityService).update(entity);
        verify(mapper.forResponse()).map(entity, CarSegmentDTO.class);
    }

    @Test
    void getById_shouldReturnMappedDTO_whenValidIdProvided() {
        // Arrange
        int id = 1;
        CarSegmentEntity entity = new CarSegmentEntity();
        CarSegmentDTO expectedDTO = new CarSegmentDTO();

        when(entityService.getById(id)).thenReturn(entity);
        when(mapper.forResponse().map(entity, CarSegmentDTO.class)).thenReturn(expectedDTO);

        // Act
        CarSegmentDTO resultDTO = carSegmentManager.getById(id);

        // Assert
        assertEquals(expectedDTO, resultDTO);
        verify(entityService).getById(id);
        verify(mapper.forResponse()).map(entity, CarSegmentDTO.class);
    }

    @Test
    void getAll_shouldReturnListOfMappedDTOs_whenNoArgumentProvided() {
        // Arrange
        CarSegmentEntity segment1 = new CarSegmentEntity();
        CarSegmentEntity segment2 = new CarSegmentEntity();
        CarSegmentDTO dto1 = new CarSegmentDTO();
        CarSegmentDTO dto2 = new CarSegmentDTO();
        List<CarSegmentDTO> expectedDTOList = Arrays.asList(dto1, dto2);

        when(rules.checkDataList(entityService.getAll()));
        when(mapper.forResponse().map(segment1, CarSegmentDTO.class)).thenReturn(dto1);
        when(mapper.forResponse().map(segment2, CarSegmentDTO.class)).thenReturn(dto2);

        // Act
        List<CarSegmentDTO> resultDTOList = carSegmentManager.getAll();

        // Assert
        assertEquals(expectedDTOList, resultDTOList);
        verify(rules).checkDataList(entityService.getAll());
        verify(mapper.forResponse()).map(segment1, CarSegmentDTO.class);
        verify(mapper.forResponse()).map(segment2, CarSegmentDTO.class);
    }

    @Test
    void getAllByDeletedState_shouldReturnListOfMappedDTOs_whenBooleanProvided() {
        // Arrange
        boolean isDeleted = false;
        CarSegmentEntity segment1 = new CarSegmentEntity();
        CarSegmentEntity segment2 = new CarSegmentEntity();
        CarSegmentDTO dto1 = new CarSegmentDTO();
        CarSegmentDTO dto2 = new CarSegmentDTO();
        List<CarSegmentDTO> expectedDTOList = Arrays.asList(dto1, dto2);

        when(rules.checkDataList(entityService.getAllByDeletedState(isDeleted)));
        when(mapper.forResponse().map(segment1, CarSegmentDTO.class)).thenReturn(dto1);
        when(mapper.forResponse().map(segment2, CarSegmentDTO.class)).thenReturn(dto2);

        // Act
        List<CarSegmentDTO> resultDTOList = carSegmentManager.getAllByDeletedState(isDeleted);

        // Assert
        assertEquals(expectedDTOList, resultDTOList);
        verify(rules).checkDataList(entityService.getAllByDeletedState(isDeleted));
        verify(mapper.forResponse()).map(segment1, CarSegmentDTO.class);
        verify(mapper.forResponse()).map(segment2, CarSegmentDTO.class);
    }

    @Test
    void delete_shouldInvokeHardDeleteMethod_whenHardDeleteTrue() {
        // Arrange
        int id = 1;
        boolean hardDelete = true;

        // Act
        carSegmentManager.delete(id, hardDelete);

        // Assert
        verify(entityService).delete(entityService.getById(id));
        verifyNoMoreInteractions(entityService);
    }

    @Test
    void delete_shouldInvokeSoftDeleteMethod_whenHardDeleteFalse() {
        // Arrange
        int id = 1;
        boolean hardDelete = false;

        // Act
        carSegmentManager.delete(id, hardDelete);

        // Assert
        verify(carSegmentManager).softDelete(id);
        verifyNoMoreInteractions(entityService);
    }

    @Test
    void softDelete_shouldUpdateEntityWithIsDeletedTrueAndCurrentTimestamp_whenValidIdProvided() {
        // Arrange
        int id = 1;
        CarSegmentEntity carSegment = new CarSegmentEntity();
        when(entityService.getById(id)).thenReturn(carSegment);

        // Act
        carSegmentManager.softDelete(id);

        // Assert
        verify(carSegmentManager).softDelete(id);
        verify(entityService).update(carSegment);
        assertEquals(true, carSegment.getIsDeleted());
        assertNotNull(carSegment.getDeletedAt());
    }
}

