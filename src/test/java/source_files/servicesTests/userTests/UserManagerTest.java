package source_files.servicesTests.userTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.UserDTO;
import source_files.data.models.baseEntities.UserEntity;
import source_files.dataAccess.userRepositories.UserRepository;
import source_files.services.BusinessRules.userBusinessRuless.UserBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.userServices.UserManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserManagerTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserEntityService entityService;

    @Mock
    private ModelMapperService mapper;

    @Mock
    private UserBusinessRules rules;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserManager userManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadUserByUsername() {
        String emailAddress = "test@example.com";
        UserEntity userEntity = new UserEntity();
        when(entityService.getByEmailAddress(emailAddress)).thenReturn(userEntity);

        UserDetails userDetails = userManager.loadUserByUsername(emailAddress);

        assertEquals(userEntity, userDetails);
    }

    @Test
    public void testCreateUser() {
        UserEntity userEntity = new UserEntity();

        userManager.createUser(userEntity);

        verify(entityService, times(1)).create(userEntity);
    }

    @Test
    public void testGetAll() {
        Pageable pageable = Pageable.unpaged();
        Page<UserEntity> userEntities = Page.empty();
        when(entityService.getAll(pageable)).thenReturn(userEntities);
        when(mapper.forResponse().map(any(), eq(UserDTO.class))).thenReturn(new UserDTO());

        Page<UserDTO> userDTOs = userManager.getAll(pageable);

        assertEquals(userEntities.getTotalElements(), userDTOs.toList().size());
    }

    @Test
    public void testGetById() {
        UserEntity userEntity = new UserEntity();
        when(entityService.getById(anyInt())).thenReturn(userEntity);
        when(mapper.forResponse().map(userEntity, UserDTO.class)).thenReturn(new UserDTO());

        UserDTO userDTO = userManager.getById(1);

        assertEquals(userEntity, userDTO);
    }

    // Add more test cases for other methods as needed

}

