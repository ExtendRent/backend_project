package source_files.core.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import source_files.data.Status.UserStatus;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.types.userTypes.UserRole;
import source_files.dataAccess.userRepositories.UserRepository;
import source_files.services.userServices.abstracts.UserService;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) {

        if (userRepository.count() == 0) {

            UserEntity customer = UserEntity
                    .builder()
                    .name("customer")
                    .surname("customer")
                    .phoneNumber("11111111111")
                    .emailAddress("customer@gmail.com")
                    .password(passwordEncoder.encode("pass"))
                    .authority(UserRole.CUSTOMER)
                    .status(UserStatus.VERIFIED)
                    .imagePath("https://img.memurlar.net/galeri/4599/2cc5bb86-a578-e311-a7bb-14feb5cc13c9.jpg?width=800")
                    .build();


            UserEntity admin = UserEntity
                    .builder()
                    .name("admin")
                    .surname("admin")
                    .phoneNumber("22222222222")
                    .emailAddress("admin@gmail.com")
                    .password(passwordEncoder.encode("pass"))
                    .authority(UserRole.ADMIN)
                    .status(UserStatus.VERIFIED)
                    .imagePath("https://avatars.githubusercontent.com/u/92371744?v=4")
                    .build();

            userService.createUser(customer);
            userService.createUser(admin);
        }
    }
}
