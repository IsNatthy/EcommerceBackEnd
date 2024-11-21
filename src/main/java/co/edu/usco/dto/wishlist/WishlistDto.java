package co.edu.usco.dto.wishlist;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for Wishlist.
 */
@Data
public class WishlistDto {

    /**
     * The unique identifier of the wishlist.
     */
    private Long id;

    /**
     * The unique identifier of the user who owns the wishlist.
     */
    private Long userId;

    /**
     * The unique identifier of the product in the wishlist.
     */
    private Long productId;

    /**
     * The name of the product in the wishlist.
     */
    private String productName;

    /**
     * The description of the product in the wishlist.
     */
    private String productDescription;

    /**
     * The image data of the product returned as a byte array.
     */
    private byte[] returnedImg;

    /**
     * The price of the product in the wishlist.
     */
    private Long price;
}