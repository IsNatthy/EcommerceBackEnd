package co.edu.usco.controller.customer;

import co.edu.usco.dto.wishlist.WishlistDto;
import co.edu.usco.services.customer.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * REST controller for managing customer wishlists.
 */
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerWishlistController {

    private final WishlistService wishlistService;

    /**
     * Adds a product to the customer's wishlist.
     *
     * @param wishlistDto the data transfer object containing the wishlist details.
     * @return a ResponseEntity containing the created WishlistDto and a 201 Created status, or a BAD_REQUEST status if something went wrong.
     * @throws IOException if an input or output exception occurred.
     */
    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDto wishlistDto) throws IOException {
        WishlistDto postedWishlistDto = wishlistService.addProductToWishlist(wishlistDto);
        if (postedWishlistDto == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);
    }

    /**
     * Retrieves the wishlist for a specific user by their ID.
     *
     * @param userId the ID of the user to retrieve the wishlist for.
     * @return a ResponseEntity containing a list of WishlistDto with wishlist details.
     */
    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDto>> getWishlistByUserId(@PathVariable Long userId) {
        List<WishlistDto> wishlistDtos = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlistDtos);
    }
}