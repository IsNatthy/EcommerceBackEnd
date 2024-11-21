package co.edu.usco.dto.user;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for user signup request.
 */
@Data
public class SignupRequest {

    /**
     * The email of the user signing up.
     */
    private String email;

    /**
     * The password of the user signing up.
     */
    private String password;

    /**
     * The name of the user signing up.
     */
    private String name;

}