package source_files.servicesTests.userTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.EmployeeDTO;
import source_files.data.models.imageEntities.UserImageEntity;
import source_files.data.models.userEntities.EmployeeEntity;
import source_files.data.requests.userRequests.CreateEmployeeRequest;
import source_files.data.requests.userRequests.UpdateEmployeeRequest;
import source_files.services.BusinessRules.userBusinessRuless.EmployeeBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.EmployeeEntityService;
import source_files.services.systemServices.ImageServices.UserImageService;
import source_files.services.userServices.EmployeeManager;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeManagerTest {

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private EmployeeEntityService entityService;

    @Mock
    private EmployeeBusinessRules employeeBusinessRules;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserImageService userImageService;

    @InjectMocks
    private EmployeeManager employeeManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        when(modelMapperService.forRequest().map(any(), eq(EmployeeEntity.class))).thenReturn(employeeEntity);
        when(employeeBusinessRules.fixCreateEmployeeRequest(createEmployeeRequest)).thenReturn(createEmployeeRequest);
        when(employeeBusinessRules.checkCreateEmployeeRequest(createEmployeeRequest)).thenReturn(createEmployeeRequest);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userImageService.getById(anyInt())).thenReturn(new UserImageEntity());

        employeeManager.create(createEmployeeRequest);

        verify(entityService, times(1)).create(employeeEntity);
    }

    @Test
    public void testUpdateEmployee() {
        UpdateEmployeeRequest updateEmployeeRequest = new UpdateEmployeeRequest();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        when(entityService.getById(updateEmployeeRequest.getId())).thenReturn(employeeEntity);
        when(employeeBusinessRules.fixUpdateEmployeeRequest(updateEmployeeRequest)).thenReturn(updateEmployeeRequest);
        when(employeeBusinessRules.checkUpdateEmployeeRequest(updateEmployeeRequest)).thenReturn(updateEmployeeRequest);

        EmployeeDTO updatedEmployee = employeeManager.update(updateEmployeeRequest);

        assertEquals(employeeEntity, updatedEmployee);
    }

    @Test
    public void testGetById() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        when(entityService.getById(anyInt())).thenReturn(employeeEntity);

        EmployeeDTO employeeDTO = employeeManager.getById(1);

        assertEquals(employeeEntity, employeeDTO);
    }

    @Test
    public void testGetAll() {
        List<EmployeeEntity> employeeEntities = Collections.singletonList(new EmployeeEntity());
        when(entityService.getAll()).thenReturn(employeeEntities);

        List<EmployeeDTO> employeeDTOs = employeeManager.getAll();

        assertEquals(employeeEntities.size(), employeeDTOs.size());
    }

    // Add more test cases for other methods as needed

}

