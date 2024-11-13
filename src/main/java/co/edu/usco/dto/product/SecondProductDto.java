package co.edu.usco.dto.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data Transfer Object (DTO) used for creating or updating a product,
 * especially when handling file uploads.
 */
@Data
public class SecondProductDto {

    private Long id;

    private String name;

    private String rating;

    private Long availableQuantity;

    private Long price;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;

    private String categoryId;

}
