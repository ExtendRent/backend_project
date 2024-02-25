package source_files.servicesTests.vehicleFeaturesTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import source_files.data.DTO.Mappers.ModelMapperManager;
import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.ColorBusinessRules;
import source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers.ColorEntityManager;
import source_files.services.vehicleFeaturesServices.BrandManager;
import source_files.services.vehicleFeaturesServices.ColorManager;

import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ColorServiceTests {




    private ColorManager colorManager;

    @Mock
    private ColorEntityManager colorEntityManager;

    @Mock
    private ModelMapperManager modelMapperManager;

    @Mock
    private ColorBusinessRules colorBusinessRules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        colorManager = new ColorManager(colorEntityManager, modelMapperManager, colorBusinessRules);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    public void testCreate() {
        // Given
        CreateColorRequest createColorRequest = new CreateColorRequest();
        ColorEntity mappedColorEntity = new ColorEntity();
        mappedColorEntity.setName("TestColor");

        ModelMapper modelMapperMock = Mockito.mock(ModelMapper.class);

        Mockito.when(modelMapperManager.forRequest())
                .thenReturn(modelMapperMock);

        Mockito.when(colorBusinessRules.checkCreateColorRequest(any()))
                .thenReturn(createColorRequest);

        Mockito.when(colorBusinessRules.fixCreateColorRequest(any()))
                .thenReturn(createColorRequest);

        Mockito.when(modelMapperMock.map(any(), eq(ColorEntity.class)))
                .thenReturn(mappedColorEntity);

        // When
        colorManager.create(createColorRequest);

        // Then
        verify(colorEntityManager, times(1)).create(eq(mappedColorEntity));
    }

    @Test
    public void testUpdate() {
        // Given
        UpdateColorRequest updateColorRequest = new UpdateColorRequest();
        updateColorRequest.setId(1);
        updateColorRequest.setName("UpdatedColor");

        ColorEntity mappedColorEntity = new ColorEntity();
        mappedColorEntity.setId(updateColorRequest.getId());
        mappedColorEntity.setName(updateColorRequest.getName());

        ModelMapper modelMapperMock = Mockito.mock(ModelMapper.class);

        Mockito.when(modelMapperManager.forRequest())
                .thenReturn(modelMapperMock);

        Mockito.when(colorBusinessRules.checkUpdateColorRequest(any()))
                .thenReturn(updateColorRequest);

        Mockito.when(colorBusinessRules.fixUpdateColorRequest(any()))
                .thenReturn(updateColorRequest);

        Mockito.when(modelMapperMock.map(any(), eq(ColorEntity.class)))
                .thenReturn(mappedColorEntity);

        // When
        colorManager.update(updateColorRequest);

        // Then
        verify(colorEntityManager, times(1)).update(eq(mappedColorEntity));
    }

    @Test
    public void testGetById() {
        // Given
        int colorId = 1;
        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setId(colorId);
        colorEntity.setName("TestColor");

        ColorDTO colorDTO = new ColorDTO(colorId, "TestColor", false);

        Mockito.when(colorEntityManager.getById(eq(colorId))).thenReturn(colorEntity);
        Mockito.when(colorManager.getById(eq(colorId))).thenReturn(colorDTO);

        // When
        ColorEntity resultEntity = colorEntityManager.getById(colorId);
        ColorDTO resultDTO = colorManager.getById(colorId);

        // Then
        assertNotNull(resultEntity);
        assertEquals(colorId, resultEntity.getId());
        assertEquals("TestColor", resultEntity.getName());

        assertNotNull(resultDTO);
        assertEquals(colorId, resultDTO.getId());
        assertEquals("TestColor", resultDTO.getName());
    }


    @Test
    public void testGetAll() {
        // Given
        List<ColorEntity> colorList = Arrays.asList(new ColorEntity(), new ColorEntity());
        when(colorEntityManager.getAll())
                .thenReturn(colorList);

        // When
        colorManager.getAll();

        // Then
        verify(colorBusinessRules, times(1)).checkDataList(eq(colorList));
        verify(modelMapperManager, times(1)).forResponse();
        verify(modelMapperManager.forResponse(), times(colorList.size())).map(any(), eq(ColorEntity.class));
    }

    @Test
    public void testGetAllByDeletedState() {
        // Given
        boolean isDeleted = false;
        List<ColorEntity> colorList = Arrays.asList(new ColorEntity(), new ColorEntity());

        ModelMapper mockModelMapper = Mockito.mock(ModelMapper.class);
        when(modelMapperManager.forResponse()).thenReturn(mockModelMapper);

        when(colorEntityManager.getAllByDeletedState(eq(isDeleted)))
                .thenReturn(colorList);

        // When
        colorManager.getAllByDeletedState(isDeleted);

        // Then
        verify(colorBusinessRules, times(1)).checkDataList(eq(colorList));
        verify(modelMapperManager, times(1)).forResponse();

        // Güncellenmiş kısım: forResponse metodunun çağrıldığını kontrol et
        verify(mockModelMapper, times(colorList.size())).map(any(), eq(ColorDTO.class));
    }


    @Test
    public void testDelete() {
        // Given
        int colorId = 1;
        ColorEntity colorEntity = new ColorEntity();
        when(colorEntityManager.getById(eq(colorId)))
                .thenReturn(colorEntity);

        // When
        colorManager.delete(colorId, false);

        // Then
        verify(colorEntityManager, times(1)).getById(eq(colorId));
        verify(colorEntityManager, times(1)).delete(eq(colorEntity));
    }

    @Test
    public void testSoftDelete() {
        // Given
        int colorId = 1;
        ColorEntity colorEntity = new ColorEntity();
        when(colorEntityManager.getById(eq(colorId)))
                .thenReturn(colorEntity);

        // When
        colorManager.softDelete(colorId);

        // Then
        verify(colorEntityManager, times(1)).getById(eq(colorId));
        verify(colorEntityManager, times(1)).update(eq(colorEntity));
    }
}
