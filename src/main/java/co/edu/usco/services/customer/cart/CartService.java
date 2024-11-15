package co.edu.usco.services.customer.cart;

import co.edu.usco.dto.cart.CartItemsDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart(CartItemsDto cartItemsDto);
}
