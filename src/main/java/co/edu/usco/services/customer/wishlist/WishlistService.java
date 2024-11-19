package co.edu.usco.services.customer.wishlist;

import co.edu.usco.dto.wishlist.WishlistDto;

import java.util.List;

/**
 * Service interface for managing customer wishlists.
 */
public interface WishlistService {

    /**
     * Adds a product to the wishlist.
     *
     * @param wishlistDto the data transfer object containing the wishlist details.
     * @return the WishlistDto containing the updated wishlist details.
     */
    WishlistDto addProductToWishlist(WishlistDto wishlistDto);

    /**
     * Retrieves the wishlist by user ID.
     *
     * @param userId the ID of the user whose wishlist is to be retrieved.
     * @return a list of WishlistDto containing the user's wishlist items.
     */
    List<WishlistDto> getWishlistByUserId(Long userId);
}