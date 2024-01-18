package source_files.services.userServices.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;
import source_files.data.requests.auth.LoginRequest;
import source_files.data.requests.userRequests.AddCustomerRequest;

public interface UserService extends UserDetailsService {
    void register(AddCustomerRequest addCustomerRequest);

    void login(LoginRequest loginRequest);
}
