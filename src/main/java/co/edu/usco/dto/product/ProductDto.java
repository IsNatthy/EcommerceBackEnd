package co.edu.usco.dto.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data Transfer Object (DTO) used for transferring product data without
 * handling file uploads, typically for retrieving and displaying product information.
 */
@Data
public class ProductDto {

    private Long id;

    private String name;

    private Long price;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;

    private Long categoryId;

    private String categoryName;

}