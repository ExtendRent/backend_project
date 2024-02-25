package source_files.servicesTests.vehicleFeaturesTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.ShiftTypeDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.ShiftTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.CreateShiftTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.UpdateShiftTypeRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.ShiftTypeBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ShiftTypeEntityService;
import source_files.services.vehicleFeaturesServices.ShiftTypeManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ShiftTypeManagerTest {

    @InjectMocks
    private ShiftTypeManager shiftTypeManager;

    @Mock
    private ShiftTypeEntityService shiftTypeEntityService;

    @Mock
    private ModelMapperService mapper;

    @Mock
    private ShiftTypeBusinessRules rules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldInvokeCreateMethodOfEntityService_whenCreateShiftTypeRequestProvided() {
        // Arrange
        CreateShiftTypeRequest request = new CreateShiftTypeRequest();
        ShiftTypeEntity entity = new ShiftTypeEntity();

        when(mapper.forRequest().map(request, ShiftTypeEntity.class)).thenReturn(entity);

        // Act
        shiftTypeManager.create(request);

        // Assert
        verify(mapper.forRequest()).map(request, ShiftTypeEntity.class);
        verify(shiftTypeEntityService).create(entity);
    }

    @Test
    void update_shouldReturnMappedDTO_whenUpdateShiftTypeRequestProvided() {
        // Arrange
        UpdateShiftTypeRequest request = new UpdateShiftTypeRequest();
        ShiftTypeEntity entity = new ShiftTypeEntity();
        ShiftTypeDTO expectedDTO = new ShiftTypeDTO();

        when(mapper.forRequest().map(request, ShiftTypeEntity.class)).thenReturn(entity);
        when(shiftTypeEntityService.update(entity)).thenReturn(entity);
        when(mapper.forResponse().map(entity, ShiftTypeDTO.class)).thenReturn(expectedDTO);

        // Act
        ShiftTypeDTO resultDTO = shiftTypeManager.update(request);

        // Assert
        assertEquals(expectedDTO, resultDTO);
        verify(mapper.forRequest()).map(request, ShiftTypeEntity.class);
        verify(shiftTypeEntityService).update(entity);
        verify(mapper.forResponse()).map(entity, ShiftTypeDTO.class);
    }

    @Test
    void getById_shouldReturnMappedDTO_whenValidIdProvided() {
        // Arrange
        int id = 1;
        ShiftTypeEntity entity = new ShiftTypeEntity();
        ShiftTypeDTO expectedDTO = new ShiftTypeDTO();

        when(shiftTypeEntityService.getById(id)).thenReturn(entity);
        when(mapper.forResponse().map(entity, ShiftTypeDTO.class)).thenReturn(expectedDTO);

        // Act
        ShiftTypeDTO resultDTO = shiftTypeManager.getById(id);

        // Assert
        assertEquals(expectedDTO, resultDTO);
        verify(shiftTypeEntityService).getById(id);
        verify(mapper.forResponse()).map(entity, ShiftTypeDTO.class);
    }

    @Test
    void getAll_shouldReturnListOfMappedDTOs_whenNoArgumentProvided() {
        // Arrange
        ShiftTypeEntity shiftType1 = new ShiftTypeEntity();
        ShiftTypeEntity shiftType2 = new ShiftTypeEntity();
        ShiftTypeDTO dto1 = new ShiftTypeDTO();
        ShiftTypeDTO dto2 = new ShiftTypeDTO();
        List<ShiftTypeDTO> expectedDTOList = Arrays.asList(dto1, dto2);

        when(rules.checkDataList(shiftTypeEntityService.getAll()));
        when(mapper.forResponse().map(shiftType1, ShiftTypeDTO.class)).thenReturn(dto1);
        when(mapper.forResponse().map(shiftType2, ShiftTypeDTO.class)).thenReturn(dto2);

        // Act
        List<ShiftTypeDTO> resultDTOList = shiftTypeManager.getAll();

        // Assert
        assertEquals(expectedDTOList, resultDTOList);
        verify(rules).checkDataList(shiftTypeEntityService.getAll());
        verify(mapper.forResponse()).map(shiftType1, ShiftTypeDTO.class);
        verify(mapper.forResponse()).map(shiftType2, ShiftTypeDTO.class);
    }

    @Test
    void getAllByDeletedState_shouldReturnListOfMappedDTOs_whenBooleanProvided() {
        // Arrange
        boolean isDeleted = false;
        ShiftTypeEntity shiftType1 = new ShiftTypeEntity();
        ShiftTypeEntity shiftType2 = new ShiftTypeEntity();
        ShiftTypeDTO dto1 = new ShiftTypeDTO();
        ShiftTypeDTO dto2 = new ShiftTypeDTO();
        List<ShiftTypeDTO> expectedDTOList = Arrays.asList(dto1, dto2);

        when(rules.checkDataList(shiftTypeEntityService.getAllByDeletedState(isDeleted)));
        when(mapper.forResponse().map(shiftType1, ShiftTypeDTO.class)).thenReturn(dto1);
        when(mapper.forResponse().map(shiftType2, ShiftTypeDTO.class)).thenReturn(dto2);

        // Act
        List<ShiftTypeDTO> resultDTOList = shiftTypeManager.getAllByDeletedState(isDeleted);

        // Assert
        assertEquals(expectedDTOList, resultDTOList);
        verify(rules).checkDataList(shiftTypeEntityService.getAllByDeletedState(isDeleted));
        verify(mapper.forResponse()).map(shiftType1, ShiftTypeDTO.class);
        verify(mapper.forResponse()).map(shiftType2, ShiftTypeDTO.class);
    }

    @Test
    void delete_shouldInvokeHardDeleteMethod_whenHardDeleteTrue() {
        // Arrange
        int id = 1;
        boolean hardDelete = true;

        // Act
        shiftTypeManager.delete(id, hardDelete);

        // Assert
        verify(shiftTypeEntityService).delete(shiftTypeEntityService.getById(id));
        verifyNoMoreInteractions(shiftTypeEntityService);
    }

    @Test
    void delete_shouldInvokeSoftDeleteMethod_whenHardDeleteFalse() {
        // Arrange
        int id = 1;
        boolean hardDelete = false;

        // Act
        shiftTypeManager.delete(id, hardDelete);

        // Assert
        verify(shiftTypeManager).softDelete(id);
        verifyNoMoreInteractions(shiftTypeEntityService);
    }

    @Test
    void softDelete_shouldUpdateEntityWithIsDeletedTrueAndCurrentTimestamp_whenValidIdProvided() {
        // Arrange
        int id = 1;
        ShiftTypeEntity shiftTypeEntity = new ShiftTypeEntity();
        when(shiftTypeEntityService.getById(id)).thenReturn(shiftTypeEntity);

        // Act
        shiftTypeManager.softDelete(id);

        // Assert
        verify(shiftTypeManager).softDelete(id);
        verify(shiftTypeEntityService).update(shiftTypeEntity);
        assertEquals(true, shiftTypeEntity.getIsDeleted());
        assertNotNull(shiftTypeEntity.getDeletedAt());
    }
}
