package source_files.servicesTests.vehicleFeaturesTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.VehicleStatusDTO;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.models.vehicleEntities.vehicleFeatures.VehicleStatusEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.CreateVehicleStatusRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.UpdateVehicleStatusRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.VehicleStatusBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.VehicleStatusEntityService;
import source_files.services.vehicleFeaturesServices.VehicleStatusManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class VehicleStatusManagerTest {

    @InjectMocks
    private VehicleStatusManager vehicleStatusManager;

    @Mock
    private VehicleStatusEntityService vehicleStatusEntityService;

    @Mock
    private ModelMapperService mapper;

    @Mock
    private VehicleStatusBusinessRules rules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldInvokeCreateMethodOfEntityService_whenCreateVehicleStatusRequestProvided() {
        // Arrange
        CreateVehicleStatusRequest request = new CreateVehicleStatusRequest();
        VehicleStatusEntity entity = new VehicleStatusEntity();

        when(mapper.forRequest().map(request, VehicleStatusEntity.class)).thenReturn(entity);

        // Act
        vehicleStatusManager.create(request);

        // Assert
        verify(mapper.forRequest()).map(request, VehicleStatusEntity.class);
        verify(vehicleStatusEntityService).create(entity);
    }

    @Test
    void update_shouldReturnMappedDTO_whenUpdateVehicleStatusRequestProvided() {
        // Arrange
        UpdateVehicleStatusRequest request = new UpdateVehicleStatusRequest();
        VehicleStatusEntity entity = new VehicleStatusEntity();
        VehicleStatusDTO expectedDTO = new VehicleStatusDTO();

        when(vehicleStatusEntityService.getById(request.getId())).thenReturn(entity);
        when(mapper.forResponse().map(vehicleStatusEntityService.update(entity), VehicleStatusDTO.class)).thenReturn(expectedDTO);

        // Act
        VehicleStatusDTO resultDTO = vehicleStatusManager.update(request);

        // Assert
        assertEquals(expectedDTO, resultDTO);
        verify(vehicleStatusEntityService).getById(request.getId());
        verify(mapper.forResponse()).map(vehicleStatusEntityService.update(entity), VehicleStatusDTO.class);
    }

    @Test
    void getById_shouldReturnMappedDTO_whenValidIdProvided() {
        // Arrange
        int id = 1;
        VehicleStatusEntity entity = new VehicleStatusEntity();
        VehicleStatusDTO expectedDTO = new VehicleStatusDTO();

        when(vehicleStatusEntityService.getById(id)).thenReturn(entity);
        when(mapper.forResponse().map(entity, VehicleStatusDTO.class)).thenReturn(expectedDTO);

        // Act
        VehicleStatusDTO resultDTO = vehicleStatusManager.getById(id);

        // Assert
        assertEquals(expectedDTO, resultDTO);
        verify(vehicleStatusEntityService).getById(id);
        verify(mapper.forResponse()).map(entity, VehicleStatusDTO.class);
    }

    @Test
    void getAll_shouldReturnListOfMappedDTOs_whenNoArgumentProvided() {
        // Arrange
        VehicleStatusEntity status1 = new VehicleStatusEntity();
        VehicleStatusEntity status2 = new VehicleStatusEntity();
        VehicleStatusDTO dto1 = new VehicleStatusDTO();
        VehicleStatusDTO dto2 = new VehicleStatusDTO();
        List<VehicleStatusDTO> expectedDTOList = Arrays.asList(dto1, dto2);

        when(rules.checkDataList(vehicleStatusEntityService.getAll()));
        when(mapper.forResponse().map(status1, VehicleStatusDTO.class)).thenReturn(dto1);
        when(mapper.forResponse().map(status2, VehicleStatusDTO.class)).thenReturn(dto2);

        // Act
        List<VehicleStatusDTO> resultDTOList = vehicleStatusManager.getAll();

        // Assert
        assertEquals(expectedDTOList, resultDTOList);
        verify(rules).checkDataList(vehicleStatusEntityService.getAll());
        verify(mapper.forResponse()).map(status1, VehicleStatusDTO.class);
        verify(mapper.forResponse()).map(status2, VehicleStatusDTO.class);
    }

    @Test
    void getByStatus_shouldReturnMappedDTO_whenValidStatusProvided() {
        // Arrange
        DefaultVehicleStatus status = DefaultVehicleStatus.AVAILABLE;
        VehicleStatusEntity entity = new VehicleStatusEntity();
        VehicleStatusDTO expectedDTO = new VehicleStatusDTO();

        when(vehicleStatusEntityService.getByStatus(status)).thenReturn(entity);
        when(mapper.forResponse().map(entity, VehicleStatusDTO.class)).thenReturn(expectedDTO);

        // Act
        VehicleStatusDTO resultDTO = vehicleStatusManager.getByStatus(status);

        // Assert
        assertEquals(expectedDTO, resultDTO);
        verify(vehicleStatusEntityService).getByStatus(status);
        verify(mapper.forResponse()).map(entity, VehicleStatusDTO.class);
    }

    @Test
    void delete_shouldInvokeHardDeleteMethod_whenHardDeleteTrue() {
        // Arrange
        int id = 1;
        boolean hardDelete = true;

        // Act
        vehicleStatusManager.delete(id, hardDelete);

        // Assert
        verify(vehicleStatusEntityService).delete(vehicleStatusEntityService.getById(id));
        verifyNoMoreInteractions(vehicleStatusEntityService);
    }

    @Test
    void delete_shouldInvokeSoftDeleteMethod_whenHardDeleteFalse() {
        // Arrange
        int id = 1;
        boolean hardDelete = false;

        // Act
        vehicleStatusManager.delete(id, hardDelete);

        // Assert
        verify(vehicleStatusManager).softDelete(id);
        verifyNoMoreInteractions(vehicleStatusEntityService);
    }

    @Test
    void softDelete_shouldUpdateEntityWithIsDeletedTrueAndCurrentTimestamp_whenValidIdProvided() {
        // Arrange
        int id = 1;
        VehicleStatusEntity vehicleStatusEntity = new VehicleStatusEntity();
        when(vehicleStatusEntityService.getById(id)).thenReturn(vehicleStatusEntity);

        // Act
        vehicleStatusManager.softDelete(id);

        // Assert
        verify(vehicleStatusEntityService).update(vehicleStatusEntity);
        assertEquals(true, vehicleStatusEntity.getIsDeleted());
        assertNotNull(vehicleStatusEntity.getDeletedAt());
    }
}

