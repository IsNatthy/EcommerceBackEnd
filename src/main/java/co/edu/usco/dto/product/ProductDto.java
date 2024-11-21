package co.edu.usco.dto.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data Transfer Object (DTO) used for transferring product data without
 * handling file uploads, typically for retrieving and displaying product information.
 */
@Data
public class ProductDto {

    /**
     * The unique identifier of the product.
     */
    private Long id;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The price of the product.
     */
    private Long price;

    /**
     * The description of the product.
     */
    private String description;

    /**
     * The image file of the product.
     */
    private MultipartFile img;

    /**
     * The image data returned as a byte array.
     */
    private byte[] returnedImg;

    /**
     * The unique identifier of the category to which the product belongs.
     */
    private Long categoryId;

    /**
     * The name of the category to which the product belongs.
     */
    private String categoryName;

}