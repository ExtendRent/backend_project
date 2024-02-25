package source_files.servicesTests.vehicleFeaturesTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.FuelTypeDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.FuelTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.CreateFuelTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.UpdateFuelTypeRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.FuelTypeBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.FuelTypeEntityService;
import source_files.services.vehicleFeaturesServices.FuelTypeManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FuelTypeManagerTest {

    @InjectMocks
    private FuelTypeManager fuelTypeManager;

    @Mock
    private FuelTypeEntityService fuelTypeEntityService;

    @Mock
    private ModelMapperService mapper;

    @Mock
    private FuelTypeBusinessRules rules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldInvokeCreateMethodOfEntityService_whenCreateFuelTypeRequestProvided() {
        // Arrange
        CreateFuelTypeRequest request = new CreateFuelTypeRequest();
        FuelTypeEntity entity = new FuelTypeEntity();

        when(mapper.forRequest().map(request, FuelTypeEntity.class)).thenReturn(entity);

        // Act
        fuelTypeManager.create(request);

        // Assert
        verify(mapper.forRequest()).map(request, FuelTypeEntity.class);
        verify(fuelTypeEntityService).create(entity);
    }

    @Test
    void update_shouldReturnMappedDTO_whenUpdateFuelTypeRequestProvided() {
        // Arrange
        UpdateFuelTypeRequest request = new UpdateFuelTypeRequest();
        FuelTypeEntity entity = new FuelTypeEntity();
        FuelTypeDTO expectedDTO = new FuelTypeDTO();

        when(mapper.forRequest().map(request, FuelTypeEntity.class)).thenReturn(entity);
        when(fuelTypeEntityService.update(entity)).thenReturn(entity);
        when(mapper.forResponse().map(entity, FuelTypeDTO.class)).thenReturn(expectedDTO);

        // Act
        FuelTypeDTO resultDTO = fuelTypeManager.update(request);

        // Assert
        assertEquals(expectedDTO, resultDTO);
        verify(mapper.forRequest()).map(request, FuelTypeEntity.class);
        verify(fuelTypeEntityService).update(entity);
        verify(mapper.forResponse()).map(entity, FuelTypeDTO.class);
    }

    @Test
    void getById_shouldReturnMappedDTO_whenValidIdProvided() {
        // Arrange
        int id = 1;
        FuelTypeEntity entity = new FuelTypeEntity();
        FuelTypeDTO expectedDTO = new FuelTypeDTO();

        when(fuelTypeEntityService.getById(id)).thenReturn(entity);
        when(mapper.forResponse().map(entity, FuelTypeDTO.class)).thenReturn(expectedDTO);

        // Act
        FuelTypeDTO resultDTO = fuelTypeManager.getById(id);

        // Assert
        assertEquals(expectedDTO, resultDTO);
        verify(fuelTypeEntityService).getById(id);
        verify(mapper.forResponse()).map(entity, FuelTypeDTO.class);
    }

    @Test
    void getAll_shouldReturnListOfMappedDTOs_whenNoArgumentProvided() {
        // Arrange
        FuelTypeEntity fuelType1 = new FuelTypeEntity();
        FuelTypeEntity fuelType2 = new FuelTypeEntity();
        FuelTypeDTO dto1 = new FuelTypeDTO();
        FuelTypeDTO dto2 = new FuelTypeDTO();
        List<FuelTypeDTO> expectedDTOList = Arrays.asList(dto1, dto2);

        when(rules.checkDataList(fuelTypeEntityService.getAll()));
        when(mapper.forResponse().map(fuelType1, FuelTypeDTO.class)).thenReturn(dto1);
        when(mapper.forResponse().map(fuelType2, FuelTypeDTO.class)).thenReturn(dto2);

        // Act
        List<FuelTypeDTO> resultDTOList = fuelTypeManager.getAll();

        // Assert
        assertEquals(expectedDTOList, resultDTOList);
        verify(rules).checkDataList(fuelTypeEntityService.getAll());
        verify(mapper.forResponse()).map(fuelType1, FuelTypeDTO.class);
        verify(mapper.forResponse()).map(fuelType2, FuelTypeDTO.class);
    }

    @Test
    void getAllByDeletedState_shouldReturnListOfMappedDTOs_whenBooleanProvided() {
        // Arrange
        boolean isDeleted = false;
        FuelTypeEntity fuelType1 = new FuelTypeEntity();
        FuelTypeEntity fuelType2 = new FuelTypeEntity();
        FuelTypeDTO dto1 = new FuelTypeDTO();
        FuelTypeDTO dto2 = new FuelTypeDTO();
        List<FuelTypeDTO> expectedDTOList = Arrays.asList(dto1, dto2);

        when(rules.checkDataList(fuelTypeEntityService.getAllByDeletedState(isDeleted)));
        when(mapper.forResponse().map(fuelType1, FuelTypeDTO.class)).thenReturn(dto1);
        when(mapper.forResponse().map(fuelType2, FuelTypeDTO.class)).thenReturn(dto2);

        // Act
        List<FuelTypeDTO> resultDTOList = fuelTypeManager.getAllByDeletedState(isDeleted);

        // Assert
        assertEquals(expectedDTOList, resultDTOList);
        verify(rules).checkDataList(fuelTypeEntityService.getAllByDeletedState(isDeleted));
        verify(mapper.forResponse()).map(fuelType1, FuelTypeDTO.class);
        verify(mapper.forResponse()).map(fuelType2, FuelTypeDTO.class);
    }

    @Test
    void delete_shouldInvokeHardDeleteMethod_whenHardDeleteTrue() {
        // Arrange
        int id = 1;
        boolean hardDelete = true;

        // Act
        fuelTypeManager.delete(id, hardDelete);

        // Assert
        verify(fuelTypeEntityService).delete(fuelTypeEntityService.getById(id));
        verifyNoMoreInteractions(fuelTypeEntityService);
    }

    @Test
    void delete_shouldInvokeSoftDeleteMethod_whenHardDeleteFalse() {
        // Arrange
        int id = 1;
        boolean hardDelete = false;

        // Act
        fuelTypeManager.delete(id, hardDelete);

        // Assert
        verify(fuelTypeManager).softDelete(id);
        verifyNoMoreInteractions(fuelTypeEntityService);
    }

    @Test
    void softDelete_shouldUpdateEntityWithIsDeletedTrueAndCurrentTimestamp_whenValidIdProvided() {
        // Arrange
        int id = 1;
        FuelTypeEntity fuelTypeEntity = new FuelTypeEntity();
        when(fuelTypeEntityService.getById(id)).thenReturn(fuelTypeEntity);

        // Act
        fuelTypeManager.softDelete(id);

        // Assert
        verify(fuelTypeManager).softDelete(id);
        verify(fuelTypeEntityService).update(fuelTypeEntity);
        assertEquals(true, fuelTypeEntity.getIsDeleted());
        assertNotNull(fuelTypeEntity.getDeletedAt());
    }
}
