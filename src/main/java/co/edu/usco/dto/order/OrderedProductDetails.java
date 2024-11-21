package co.edu.usco.dto.order;

import lombok.Data;

/**
 * Data Transfer Object for Ordered Product Details.
 */
@Data
public class OrderedProductDetails {

    /**
     * The unique identifier of the ordered product.
     */
    private Long id;

    /**
     * The name of the ordered product.
     */
    private String name;

    /**
     * The price of the ordered product.
     */
    private Long productPrice;

    /**
     * The quantity of the ordered product.
     */
    private Long quantity;

    /**
     * The image of the ordered product in byte array format.
     */
    private byte[] returnedImg;
}