package co.edu.usco.entity;

import co.edu.usco.dto.cart.CartItemsDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entity representing items in a shopping cart.
 */
@Entity
@Data
public class CartItems {

    /**
     * The unique identifier of the cart item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * The product associated with the cart item.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    /**
     * The user who owns the cart item.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    /**
     * The order associated with the cart item.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * Converts the CartItems entity to a CartItemsDto.
     *
     * @return the CartItemsDto representation of the cart item.
     */
    public CartItemsDto getCartDto() {
        CartItemsDto cartItemsDto = new CartItemsDto();
        cartItemsDto.setId(id);
        cartItemsDto.setPrice(price);
        cartItemsDto.setProductId(product.getId());
        cartItemsDto.setQuantity(quantity);
        cartItemsDto.setUserId(user.getId());
        cartItemsDto.setProductName(product.getName());
        cartItemsDto.setReturnedImg(product.getImg());
        return cartItemsDto;
    }
}