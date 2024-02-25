package source_files.servicesTests.vehicleFeaturesTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.CarBodyTypeDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarBodyTypeBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;
import source_files.services.vehicleFeaturesServices.CarBodyTypeManager;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarBodyTypeManagerTest {

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private CarBodyTypeEntityService carBodyTypeEntityService;

    @Mock
    private CarBodyTypeBusinessRules carBodyTypeBusinessRules;

    @InjectMocks
    private CarBodyTypeManager carBodyTypeManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCarBodyType() {
        CreateCarBodyTypeRequest createCarBodyTypeRequest = new CreateCarBodyTypeRequest();
        CarBodyTypeEntity carBodyTypeEntity = new CarBodyTypeEntity();
        when(modelMapperService.forRequest().map(any(), eq(CarBodyTypeEntity.class))).thenReturn(carBodyTypeEntity);

        carBodyTypeManager.create(createCarBodyTypeRequest);

        verify(carBodyTypeEntityService, times(1)).create(carBodyTypeEntity);
    }

    @Test
    public void testUpdateCarBodyType() {
        UpdateCarBodyTypeRequest updateCarBodyTypeRequest = new UpdateCarBodyTypeRequest();
        CarBodyTypeEntity carBodyTypeEntity = new CarBodyTypeEntity();
        when(modelMapperService.forRequest().map(any(), eq(CarBodyTypeEntity.class))).thenReturn(carBodyTypeEntity);
        when(carBodyTypeBusinessRules.fixUpdateCarBodyTypeRequest(updateCarBodyTypeRequest)).thenReturn(updateCarBodyTypeRequest);
        when(carBodyTypeBusinessRules.checkUpdateCarBodyTypeRequest(updateCarBodyTypeRequest)).thenReturn(updateCarBodyTypeRequest);

        CarBodyTypeDTO updatedCarBodyType = carBodyTypeManager.update(updateCarBodyTypeRequest);

        assertEquals(carBodyTypeEntity, updatedCarBodyType);
    }

    @Test
    public void testGetById() {
        CarBodyTypeEntity carBodyTypeEntity = new CarBodyTypeEntity();
        when(carBodyTypeEntityService.getById(anyInt())).thenReturn(carBodyTypeEntity);
        when(modelMapperService.forResponse().map(carBodyTypeEntity, CarBodyTypeDTO.class)).thenReturn(new CarBodyTypeDTO());

        CarBodyTypeDTO carBodyTypeDTO = carBodyTypeManager.getById(1);

        assertEquals(carBodyTypeEntity, carBodyTypeDTO);
    }

    @Test
    public void testGetAll() {
        List<CarBodyTypeEntity> carBodyTypeEntities = Collections.singletonList(new CarBodyTypeEntity());
        when(carBodyTypeEntityService.getAll()).thenReturn(carBodyTypeEntities);
        when(modelMapperService.forResponse().map(any(), eq(CarBodyTypeDTO.class))).thenReturn(new CarBodyTypeDTO());

        List<CarBodyTypeDTO> carBodyTypeDTOs = carBodyTypeManager.getAll();

        assertEquals(carBodyTypeEntities.size(), carBodyTypeDTOs.size());
    }
}

