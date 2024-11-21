package co.edu.usco.dto.cart;

import lombok.Data;

/**
 * Data Transfer Object for Cart Items.
 */
@Data
public class CartItemsDto {
    /**
     * The unique identifier of the cart item.
     */
    private Long id;

    /**
     * The price of the cart item.
     */
    private Long price;

    /**
     * The quantity of the cart item.
     */
    private Long quantity;

    /**
     * The unique identifier of the product.
     */
    private Long productId;

    /**
     * The unique identifier of the order.
     */
    private Long orderId;

    /**
     * The name of the product.
     */
    private String productName;

    /**
     * The image of the product in byte array format.
     */
    private byte[] returnedImg;

    /**
     * The unique identifier of the user.
     */
    private Long userId;
}