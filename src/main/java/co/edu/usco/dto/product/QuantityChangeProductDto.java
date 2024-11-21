package co.edu.usco.dto.product;

import lombok.Data;

/**
 * Data Transfer Object for Quantity Change Product.
 */
@Data
public class QuantityChangeProductDto {

    /**
     * The unique identifier of the product.
     */
    private Long productId;

    /**
     * The unique identifier of the user.
     */
    private Long userId;

}