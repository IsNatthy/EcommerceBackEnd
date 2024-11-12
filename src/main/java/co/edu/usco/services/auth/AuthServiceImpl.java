package co.edu.usco.services.auth;

import co.edu.usco.dto.user.SignupRequest;
import co.edu.usco.dto.user.UserDto;
import co.edu.usco.entity.User;
import co.edu.usco.enums.UserRole;
import co.edu.usco.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service implementation for authentication-related operations.
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto createUser(SignupRequest signupRequest) {
        logger.info("Attempting to create user with email: {}", signupRequest.getEmail());

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);

        logger.info("Saving user entity: {}", user);
        User createdUser = userRepository.save(user);
        logger.info("User saved with ID: {}", createdUser.getId());

        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());

        return userDto;
    }

    public Boolean hasUserWithEmail(String email) {
        logger.info("Checking if user with email {} exists", email);
        return userRepository.findFirstByEmail(email).isPresent();
    }

    /**
     * Creates an admin account if it does not already exist.
     */
    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if (null == adminAccount){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }
}