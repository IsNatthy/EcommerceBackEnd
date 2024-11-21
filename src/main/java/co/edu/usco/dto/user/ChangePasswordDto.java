package co.edu.usco.dto.user;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for changing a user's password.
 */
@Data
public class ChangePasswordDto {

    /**
     * The unique identifier of the user.
     */
    private Long id;

    /**
     * The user's current password.
     */
    private String oldPassword;

    /**
     * The user's new password.
     */
    private String newPassword;
}