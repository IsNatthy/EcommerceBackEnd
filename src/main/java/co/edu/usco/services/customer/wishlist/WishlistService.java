package co.edu.usco.services.customer.wishlist;

import co.edu.usco.dto.wishlist.WishlistDto;

import java.util.List;

public interface WishlistService {
    WishlistDto addProductToWishlist(WishlistDto wishlistDto);
    List<WishlistDto> getWishlistByUserId(Long userId);
}
