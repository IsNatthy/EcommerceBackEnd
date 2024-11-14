package co.edu.usco.services.auth;

import co.edu.usco.dto.user.SignupRequest;
import co.edu.usco.dto.user.UserDto;
import co.edu.usco.entity.Order;
import co.edu.usco.entity.User;
import co.edu.usco.enums.OrderStatus;
import co.edu.usco.enums.UserRole;
import co.edu.usco.repository.OrderRepository;
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
    private OrderRepository orderRepository;

    /**
     * Creates a new user based on the provided signup request.
     *
     * @param signupRequest the signup request containing user details.
     * @return the created UserDto containing user details.
     */
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

        Order order = new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setUser(user);
        order.setStatus(OrderStatus.Pending);

        orderRepository.save(order);
        logger.info("Order created for user with ID: {}", createdUser.getId());

        UserDto createdUserDto = new UserDto();
        createdUserDto.setId(createdUser.getId());
        return createdUserDto;
    }

    /**
     * Checks if a user with the given email exists.
     *
     * @param email the email to check.
     * @return true if a user with the email exists, false otherwise.
     */
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