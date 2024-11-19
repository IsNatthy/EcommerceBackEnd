package co.edu.usco.services.auth;

import co.edu.usco.dto.user.ChangePasswordDto;
import co.edu.usco.dto.user.SignupRequest;
import co.edu.usco.dto.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service interface for authentication-related operations.
 */
@Service
public interface AuthService {

    /**
     * Creates a new user based on the provided signup request.
     *
     * @param signupRequest the signup request containing user details.
     * @return the created user as a UserDto.
     */
    UserDto createUser(SignupRequest signupRequest);

    /**
     * Checks if a user with the given email exists.
     *
     * @param email the email to check.
     * @return true if a user with the email exists, false otherwise.
     */
    Boolean hasUserWithEmail(String email);

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return the UserDto with user details.
     */
    UserDto getUserById(Long userId);

    /**
     * Updates a user's details.
     *
     * @param userDto the data transfer object containing the updated user details.
     * @return the updated UserDto.
     * @throws IOException if an input or output exception occurred.
     */
    UserDto updateUser(UserDto userDto) throws IOException;

    /**
     * Updates a user's password by their ID.
     *
     * @param changePasswordDto the data transfer object containing the password change details.
     * @return a ResponseEntity indicating the result of the operation.
     */
    ResponseEntity<?> updatePasswordById(ChangePasswordDto changePasswordDto);
}