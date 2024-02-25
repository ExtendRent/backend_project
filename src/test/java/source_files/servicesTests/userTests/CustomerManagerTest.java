package source_files.servicesTests.userTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.models.imageEntities.UserImageEntity;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.requests.userRequests.CreateCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.services.BusinessRules.userBusinessRuless.CustomerBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.CustomerEntityService;
import source_files.services.systemServices.ImageServices.UserImageService;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.services.userServices.CustomerManager;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerManagerTest {

    @Mock
    private ModelMapperService mapper;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerBusinessRules rules;

    @Mock
    private CustomerEntityService entityService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserImageService userImageService;

    @InjectMocks
    private CustomerManager customerManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCustomer() {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        CustomerEntity customerEntity = new CustomerEntity();
        when(mapper.forRequest().map(any(), eq(CustomerEntity.class))).thenReturn(customerEntity);
        when(rules.fixCreateCustomerRequest(createCustomerRequest)).thenReturn(createCustomerRequest);
        when(rules.checkCreateCustomerRequest(createCustomerRequest)).thenReturn(createCustomerRequest);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userImageService.getById(anyInt())).thenReturn(new UserImageEntity());

        customerManager.create(createCustomerRequest);

        verify(entityService, times(1)).create(customerEntity);
    }

    @Test
    public void testUpdateCustomer() {
        UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest();
        CustomerEntity customerEntity = new CustomerEntity();
        when(entityService.getById(updateCustomerRequest.getId())).thenReturn(customerEntity);
        when(rules.fixUpdateCustomerRequest(updateCustomerRequest)).thenReturn(updateCustomerRequest);
        when(rules.checkUpdateCustomerRequest(updateCustomerRequest)).thenReturn(updateCustomerRequest);

        CustomerDTO updatedCustomer = customerManager.update(updateCustomerRequest);

        Assertions.assertEquals(customerEntity, updatedCustomer);
    }

    @Test
    public void testGetById() {
        CustomerEntity customerEntity = new CustomerEntity();
        when(entityService.getById(anyInt())).thenReturn(customerEntity);

        CustomerDTO customerDTO = customerManager.getById(1);

        assertEquals(customerEntity, customerDTO);
    }

    @Test
    public void testGetAll() {
        List<CustomerEntity> customerEntities = Collections.singletonList(new CustomerEntity());
        when(entityService.getAll()).thenReturn(customerEntities);

        List<CustomerDTO> customerDTOs = customerManager.getAll();

        assertEquals(customerEntities.size(), customerDTOs.size());
    }

}
