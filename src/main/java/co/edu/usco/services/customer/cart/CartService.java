package co.edu.usco.services.customer.cart;

import co.edu.usco.dto.cart.CartItemsDto;
import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.dto.product.QuantityChangeProductDto;
import org.springframework.http.ResponseEntity;

/**
 * Service interface for managing the shopping cart for customers.
 */
public interface CartService {

    /**
     * Adds a product to the cart.
     *
     * @param cartItemsDto the data transfer object containing the cart item details.
     * @return a ResponseEntity indicating the result of the operation.
     */
    ResponseEntity<?> addProductToCart(CartItemsDto cartItemsDto);

    /**
     * Retrieves the cart by user ID.
     *
     * @param userId the ID of the user whose cart is to be retrieved.
     * @return the OrderDto containing the cart details.
     */
    OrderDto getCartByUserId(Long userId);

    /**
     * Decreases the quantity of a product in the cart.
     *
     * @param quantityChangeProductDto the data transfer object containing the product quantity change details.
     * @return the updated OrderDto containing the cart details.
     */
    OrderDto decreaseProductQuantity(QuantityChangeProductDto quantityChangeProductDto);

    /**
     * Increases the quantity of a product in the cart.
     *
     * @param quantityChangeProductDto the data transfer object containing the product quantity change details.
     * @return the updated OrderDto containing the cart details.
     */
    OrderDto increaseProductQuantity(QuantityChangeProductDto quantityChangeProductDto);
}