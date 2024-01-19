package source_files.services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.requests.auth.LoginRequest;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.dataAccess.userRepositories.UserRepository;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.userServices.abstracts.UserService;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final CustomerService customerService;
    private final UserRepository userRepository;

    @Override
    public void register(AddCustomerRequest addCustomerRequest) {
        this.customerService.add(addCustomerRequest);
    }

    @Override
    public void login(LoginRequest request) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}