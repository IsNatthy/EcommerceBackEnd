package co.edu.usco.controller.customer;

import co.edu.usco.dto.cart.CartItemsDto;
import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.dto.product.QuantityChangeProductDto;
import co.edu.usco.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing customer cart operations.
 */
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * Adds a product to the cart.
     *
     * @param cartItemsDto the DTO containing cart item details.
     * @return a ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody CartItemsDto cartItemsDto) {
        return cartService.addProductToCart(cartItemsDto);
    }

    /**
     * Retrieves the cart by user ID.
     *
     * @param userId the ID of the user.
     * @return a ResponseEntity containing the OrderDto with cart details.
     */
    @GetMapping("/cart/{userId}")
    public ResponseEntity<OrderDto> getCartByUserId(@PathVariable Long userId) {
        OrderDto orderDto = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @PostMapping("/deduction")
    public ResponseEntity<OrderDto> addMinusOnProduct(@RequestBody QuantityChangeProductDto quantityChangeProductDto) {
        OrderDto orderDto = cartService.decreaseProductQuantity(quantityChangeProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }

    @PostMapping("/addition")
    public ResponseEntity<OrderDto> increaseProductQuantity(@RequestBody QuantityChangeProductDto quantityChangeProductDto) {
        OrderDto OrderDto = cartService.increaseProductQuantity(quantityChangeProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderDto);
    }

}