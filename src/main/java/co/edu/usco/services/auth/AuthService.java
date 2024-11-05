package co.edu.usco.services.auth;

import co.edu.usco.dto.SignupRequest;
import co.edu.usco.dto.UserDto;
import org.springframework.stereotype.Service;

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
}