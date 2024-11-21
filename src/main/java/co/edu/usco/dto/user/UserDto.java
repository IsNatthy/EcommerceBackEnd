package co.edu.usco.dto.user;

import co.edu.usco.enums.UserRole;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data Transfer Object (DTO) for User.
 */
@Data
public class UserDto {

    /**
     * The unique identifier of the user.
     */
    private Long id;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The role of the user.
     */
    private UserRole role;

    /**
     * The image file of the user.
     */
    private MultipartFile img;

    /**
     * The image data returned as a byte array.
     */
    private byte[] returnedImg;

}