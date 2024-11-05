package co.edu.usco.dto;

import co.edu.usco.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private UserRole role;

}
