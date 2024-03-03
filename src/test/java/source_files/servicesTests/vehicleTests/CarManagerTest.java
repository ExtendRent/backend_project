package source_files.servicesTests.vehicleTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.CreateCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.services.BusinessRules.vehicleBusinessRules.CarBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.CarEntityService;
import source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers.VehicleStatusEntityManager;
import source_files.services.systemServices.ImageServices.CarImageService;
import source_files.services.vehicleService.CarManager;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarManagerTest {

    @Mock
    private ModelMapperService mapper;

    @Mock
    private CarEntityService entityService;

    @Mock
    private CarBusinessRules rules;
    @InjectMocks
    private CarManager carManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCar() throws IOException {
        CreateCarRequest createCarRequest = new CreateCarRequest();
        CarEntity carEntity = new CarEntity();
        when(mapper.forRequest().map(any(), eq(CarEntity.class))).thenReturn(carEntity);

        carManager.create(createCarRequest);

        verify(entityService, times(1)).create(carEntity);
    }

    @Test
    public void testGetById() {
        CarEntity carEntity = new CarEntity();
        when(entityService.getById(anyInt())).thenReturn(carEntity);
        when(mapper.forResponse().map(carEntity, CarDTO.class)).thenReturn(new CarDTO());

        CarDTO carDTO = carManager.getById(1);

        assertEquals(carEntity, carDTO);
    }

    @Test
    public void testUpdate() throws IOException {
        UpdateCarRequest updateCarRequest = new UpdateCarRequest();
        CarEntity carEntity = new CarEntity();
        when(entityService.getById(updateCarRequest.getId())).thenReturn(carEntity);
        when(rules.fixUpdateCarRequest(updateCarRequest)).thenReturn(updateCarRequest);
        when(rules.checkUpdateCarRequest(updateCarRequest)).thenReturn(updateCarRequest);

        CarDTO updatedCar = carManager.update(updateCarRequest);

        assertEquals(carEntity, updatedCar);
    }

    @Test
    public void testGetAll() {
        List<CarEntity> carEntities = Collections.singletonList(new CarEntity());
        when(entityService.getAll()).thenReturn(carEntities);
        when(mapper.forResponse().map(any(), eq(CarDTO.class))).thenReturn(new CarDTO());

        List<CarDTO> carDTOs = carManager.getAll();

        assertEquals(carEntities.size(), carDTOs.size());
    }

}

