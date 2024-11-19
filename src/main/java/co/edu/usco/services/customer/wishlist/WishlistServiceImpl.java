package co.edu.usco.services.customer.wishlist;

import co.edu.usco.dto.wishlist.WishlistDto;
import co.edu.usco.entity.Product;
import co.edu.usco.entity.User;
import co.edu.usco.entity.Wishlist;
import co.edu.usco.repository.ProductRepository;
import co.edu.usco.repository.UserRepository;
import co.edu.usco.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing customer wishlists.
 */
@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final WishlistRepository wishlistRepository;

    /**
     * Adds a product to the wishlist.
     *
     * @param wishlistDto the data transfer object containing the wishlist details.
     * @return the WishlistDto containing the updated wishlist details, or null if the product is already in the wishlist or the user/product is not found.
     */
    @Override
    public WishlistDto addProductToWishlist(WishlistDto wishlistDto) {
        if (isProductInWishlist(wishlistDto.getUserId(), wishlistDto.getProductId())) {
            return null;
        }
        Optional<Product> optionalProduct = productRepository.findById(wishlistDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(wishlistDto.getUserId());
        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            Wishlist wishlist = new Wishlist();
            wishlist.setProduct(optionalProduct.get());
            wishlist.setUser(optionalUser.get());
            Wishlist createdWishlist = wishlistRepository.save(wishlist);
            WishlistDto createdWishlistDto = new WishlistDto();
            createdWishlistDto.setId(createdWishlist.getId());
            return createdWishlistDto;
        }
        return null;
    }

    /**
     * Checks if a product is already in the user's wishlist.
     *
     * @param userId the ID of the user.
     * @param productId the ID of the product.
     * @return true if the product is in the wishlist, false otherwise.
     */
    public boolean isProductInWishlist(Long userId, Long productId) {
        return wishlistRepository.existsByUserIdAndProductId(userId, productId);
    }

    /**
     * Retrieves the wishlist by user ID.
     *
     * @param userId the ID of the user whose wishlist is to be retrieved.
     * @return a list of WishlistDto containing the user's wishlist items.
     */
    @Override
    public List<WishlistDto> getWishlistByUserId(Long userId) {
        return wishlistRepository.findAllByUser_Id(userId).stream().map(Wishlist::getWishlistDto).collect(Collectors.toList());
    }
}