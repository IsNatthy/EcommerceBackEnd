package co.edu.usco.dto.user;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for Authentication Request.
 */
@Data
public class AuthenticationRequest {

    /**
     * The username of the user attempting to authenticate.
     */
    private String username;

    /**
     * The password of the user attempting to authenticate.
     */
    private String password;

}