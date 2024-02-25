package source_files.servicesTests.vehicleFeaturesTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.BrandEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.BrandBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.BrandEntityService;
import source_files.services.systemServices.ImageServices.BrandImageService;
import source_files.services.vehicleFeaturesServices.BrandManager;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BrandManagerTest {

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private BrandEntityService entityService;

    @Mock
    private BrandBusinessRules brandBusinessRules;

    @Mock
    private BrandImageService brandImageService;

    @InjectMocks
    private BrandManager brandManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateBrand() {
        CreateBrandRequest createBrandRequest = new CreateBrandRequest();
        BrandEntity brandEntity = new BrandEntity();
        when(modelMapperService.forRequest().map(any(), eq(BrandEntity.class))).thenReturn(brandEntity);

        brandManager.create(createBrandRequest);

        verify(entityService, times(1)).create(brandEntity);
    }

    @Test
    public void testUpdateBrand() {
        UpdateBrandRequest updateBrandRequest = new UpdateBrandRequest();
        BrandEntity brandEntity = new BrandEntity();
        when(entityService.getById(updateBrandRequest.getId())).thenReturn(brandEntity);
        when(brandBusinessRules.fixUpdateBrandRequest(updateBrandRequest)).thenReturn(updateBrandRequest);
        when(brandBusinessRules.checkUpdateBrandRequest(updateBrandRequest)).thenReturn(updateBrandRequest);

        BrandDTO updatedBrand = brandManager.update(updateBrandRequest);

        assertEquals(brandEntity, updatedBrand);
    }

    @Test
    public void testGetById() {
        BrandEntity brandEntity = new BrandEntity();
        when(entityService.getById(anyInt())).thenReturn(brandEntity);
        when(modelMapperService.forResponse().map(brandEntity, BrandDTO.class)).thenReturn(new BrandDTO());

        BrandDTO brandDTO = brandManager.getById(1);

        assertEquals(brandEntity, brandDTO);
    }

    @Test
    public void testGetAll() {
        List<BrandEntity> brandEntities = Collections.singletonList(new BrandEntity());
        when(entityService.getAll()).thenReturn(brandEntities);
        when(modelMapperService.forResponse().map(any(), eq(BrandDTO.class))).thenReturn(new BrandDTO());

        List<BrandDTO> brandDTOs = brandManager.getAll();

        assertEquals(brandEntities.size(), brandDTOs.size());
    }

}
