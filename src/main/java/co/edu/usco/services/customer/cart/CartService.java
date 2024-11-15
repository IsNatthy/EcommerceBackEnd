package co.edu.usco.services.customer.cart;

import co.edu.usco.dto.cart.CartItemsDto;
import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.dto.product.QuantityChangeProductDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart(CartItemsDto cartItemsDto);

    OrderDto getCartByUserId(Long userId);

    OrderDto decreaseProductQuantity(QuantityChangeProductDto quantityChangeProductDto);

    OrderDto increaseProductQuantity(QuantityChangeProductDto quantityChangeProductDto);
}
