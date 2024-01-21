package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import source_files.data.models.baseEntities.UserEntity;
import source_files.dataAccess.userRepositories.UserRepository;
import source_files.services.userServices.abstracts.UserService;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }
}