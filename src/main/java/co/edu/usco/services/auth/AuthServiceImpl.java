package co.edu.usco.services.auth;

import co.edu.usco.dto.user.ChangePasswordDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

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

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return the UserDto with user details.
     */
    @Override
    public UserDto getUserById(Long userId) {
        UserDto userDto = new UserDto();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userDto = optionalUser.get().getUserDto();
            userDto.setReturnedImg(optionalUser.get().getImg());
        }
        return userDto;
    }

    /**
     * Updates a user's details.
     *
     * @param userDto the data transfer object containing the updated user details.
     * @return the updated UserDto.
     * @throws IOException if an input or output exception occurred.
     */
    @Override
    public UserDto updateUser(UserDto userDto) throws IOException {
        Optional<User> userOptional = userRepository.findById(userDto.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmail(userDto.getEmail());
            user.setName(userDto.getName());
            if(userDto.getImg() != null){
                user.setImg(userDto.getImg().getBytes());
            }

            User updatedUser = userRepository.save(user);
            UserDto updatedUserDto = new UserDto();
            updatedUserDto.setId(updatedUser.getId());
            return updatedUserDto;
        } else {
            return null;
        }
    }

    /**
     * Updates a user's password by their ID.
     *
     * @param changePasswordDto the data transfer object containing the password change details.
     * @return a ResponseEntity indicating the result of the operation.
     */
    @Override
    public ResponseEntity<?> updatePasswordById(ChangePasswordDto changePasswordDto) {
        User user = null;
        try {
            Optional<User> userOptional = userRepository.findById(changePasswordDto.getId());
            if (userOptional.isPresent()) {
                user = userOptional.get();
                if (this.bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
                    user.setPassword(bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword()));
                    User updateUser = userRepository.save(user);
                    UserDto userDto = new UserDto();
                    userDto.setId(updateUser.getId());
                    return ResponseEntity.status(HttpStatus.OK).body(userDto);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Old password is incorrect");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}