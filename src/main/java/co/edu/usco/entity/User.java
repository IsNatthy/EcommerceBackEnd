package co.edu.usco.entity;

import co.edu.usco.dto.user.UserDto;
import co.edu.usco.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing a User.
 */
@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private UserRole role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    /**
     * Converts this User entity to a UserDto.
     *
     * @return a UserDto containing the user's details.
     */
    public UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setReturnedImg(img);
        userDto.setRole(role);
        return userDto;
    }
}