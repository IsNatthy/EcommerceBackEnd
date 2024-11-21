package co.edu.usco.dto.product;

import lombok.Data;

/**
 * Data Transfer Object for Category.
 */
@Data
public class CategoryDto {

    /**
     * The unique identifier of the category.
     */
    private Long id;

    /**
     * The name of the category.
     */
    private String name;

    /**
     * The description of the category.
     */
    private String description;

}