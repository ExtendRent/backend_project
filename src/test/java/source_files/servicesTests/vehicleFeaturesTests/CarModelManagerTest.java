package source_files.servicesTests.vehicleFeaturesTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.CreateCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarModelBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarModelEntityService;
import source_files.services.vehicleFeaturesServices.CarModelManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarModelManagerTest {

    @InjectMocks
    private CarModelManager carModelManager;

    @Mock
    private CarModelEntityService carModelEntityService;

    @Mock
    private CarModelBusinessRules carModelBusinessRules;

    @Mock
    private ModelMapperService modelMapperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ValidRequest_CallsServiceCreate() {
        // Arrange
        CreateCarModelRequest request = new CreateCarModelRequest();
        CarModelEntity expectedEntity = new CarModelEntity();
        when(carModelBusinessRules.fixCreateCarModelRequest(request)).thenReturn(request);
        when(modelMapperService.forRequest().map(request, CarModelEntity.class)).thenReturn(expectedEntity);

        // Act
        carModelManager.create(request);

        // Assert
        verify(carModelEntityService, times(1)).create(expectedEntity);
    }

    @Test
    void update_ValidRequest_CallsServiceUpdate() {
        // Arrange
        UpdateCarModelRequest request = new UpdateCarModelRequest();
        CarModelEntity expectedEntity = new CarModelEntity();
        CarModelDTO expectedDTO = new CarModelDTO();
        when(carModelBusinessRules.fixUpdateCarModelRequest(request)).thenReturn(request);
        when(modelMapperService.forRequest().map(request, CarModelEntity.class)).thenReturn(expectedEntity);
        when(modelMapperService.forRequest().map(any(), eq(CarModelEntity.class))).thenReturn(expectedEntity);
        when(modelMapperService.forResponse().map(expectedEntity, CarModelDTO.class)).thenReturn(expectedDTO);
        when(carModelEntityService.update(expectedEntity)).thenReturn(expectedEntity);

        // Act
        CarModelDTO resultDTO = carModelManager.update(request);

        // Assert
        verify(carModelEntityService, times(1)).update(expectedEntity);
        assertEquals(expectedDTO, resultDTO);
    }

    @Test
    void getById_ValidId_ReturnsMappedDTO() {
        // Arrange
        int id = 1;
        CarModelEntity expectedEntity = new CarModelEntity();
        CarModelDTO expectedDTO = new CarModelDTO();
        when(carModelEntityService.getById(id)).thenReturn(expectedEntity);
        when(modelMapperService.forResponse().map(expectedEntity, CarModelDTO.class)).thenReturn(expectedDTO);

        // Act
        CarModelDTO resultDTO = carModelManager.getById(id);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }

    @Test
    void getAllByBrandId_ValidBrandId_ReturnsMappedDTOList() {
        // Arrange
        int brandId = 1;
        List<CarModelEntity> expectedEntities = new ArrayList<>();
        expectedEntities.add(new CarModelEntity());
        List<BrandDTO> expectedDTOs = new ArrayList<>();
        expectedDTOs.add(new BrandDTO());
        when(carModelEntityService.getAllByBrandId(brandId)).thenReturn(expectedEntities);
        OngoingStubbing<? extends List<?>> listOngoingStubbing = when(carModelBusinessRules.checkDataList(expectedEntities));
        when(modelMapperService.forResponse().map(any(), eq(BrandDTO.class))).thenReturn(new BrandDTO());

        // Act
        List<BrandDTO> resultDTOs = carModelManager.getAllByBrandId(brandId);

        // Assert
        assertEquals(expectedDTOs.size(), resultDTOs.size());
    }
}
