package co.edu.usco.services.auth;

import co.edu.usco.dto.SignupRequest;
import co.edu.usco.dto.UserDto;
import co.edu.usco.entity.UserEntity;
import co.edu.usco.enums.UserRole;
import co.edu.usco.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service implementation for authentication-related operations.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Creates a new user based on the provided signup request.
     *
     * @param signupRequest the signup request containing user details.
     * @return the created user as a UserDto.
     */
    public UserDto createUser(SignupRequest signupRequest) {
        UserEntity user = new UserEntity();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        UserEntity createdUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());

        return userDto;
    }

    /**
     * Checks if a user with the given email exists.
     *
     * @param email the email to check.
     * @return true if a user with the email exists, false otherwise.
     */
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    /**
     * Creates an admin account if it does not already exist.
     */
    @PostConstruct
    public void createAdminAccount(){
        UserEntity adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if (null == adminAccount){
            UserEntity user = new UserEntity();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }
}