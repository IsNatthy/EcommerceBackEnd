package co.edu.usco.entity;

import co.edu.usco.dto.wishlist.WishlistDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entity class representing a Wishlist.
 */
@Data
@Entity
public class Wishlist {

    /**
     * Unique identifier for the Wishlist.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The product associated with the Wishlist.
     * This relationship is many-to-one and uses lazy fetching.
     * The product is deleted if the Wishlist is deleted.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    /**
     * The user associated with the Wishlist.
     * This relationship is many-to-one and uses lazy fetching.
     * The user is deleted if the Wishlist is deleted.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    /**
     * Converts the Wishlist entity to a WishlistDto.
     *
     * @return a WishlistDto containing the Wishlist details.
     */
    public WishlistDto getWishlistDto() {
        WishlistDto wishlistDto = new WishlistDto();
        wishlistDto.setId(id);
        wishlistDto.setProductId(product.getId());
        wishlistDto.setReturnedImg(product.getImg());
        wishlistDto.setProductName(product.getName());
        wishlistDto.setProductDescription(product.getDescription());
        wishlistDto.setPrice(product.getPrice());
        wishlistDto.setUserId(user.getId());
        return wishlistDto;
    }
}