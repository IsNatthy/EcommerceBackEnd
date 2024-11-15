package co.edu.usco.services.customer.cart;

import co.edu.usco.entity.Order;
import co.edu.usco.entity.Product;
import co.edu.usco.entity.User;
import co.edu.usco.enums.OrderStatus;
import co.edu.usco.repository.CartRepository;
import co.edu.usco.repository.OrderRepository;
import co.edu.usco.repository.ProductRepository;
import co.edu.usco.repository.UserRepository;
import co.edu.usco.entity.CartItems;
import co.edu.usco.dto.cart.CartItemsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for managing cart-related operations.
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Adds a product to the cart.
     *
     * @param cartItemsDto the DTO containing cart item details.
     * @return a ResponseEntity indicating the result of the operation.
     */
    @Override
    public ResponseEntity<?> addProductToCart(CartItemsDto cartItemsDto) {
        Order pendingOrder = orderRepository.findByUserIdAndStatus(cartItemsDto.getUserId(), OrderStatus.Pending);
        Optional<CartItems> cartItem = cartRepository.findByProductIdAndOrderIdAndUserId(cartItemsDto.getProductId(), pendingOrder.getId(), cartItemsDto.getUserId());
        if (cartItem.isPresent()) {
            CartItemsDto productAlreadyExistsInCart = new CartItemsDto();
            productAlreadyExistsInCart.setProductId(null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(productAlreadyExistsInCart);
        } else {
            Product product = null;
            Optional<Product> optionalProduct = productRepository.findById(cartItemsDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(cartItemsDto.getUserId());
            Order runningOrder = orderRepository.findByUserIdAndStatus(cartItemsDto.getUserId(), OrderStatus.Pending);

            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                product = optionalProduct.get();
                CartItems cart = new CartItems();
                cart.setProduct(product);
                cart.setPrice(product.getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(runningOrder);

                CartItems updatedCart = cartRepository.save(cart);

                Order order = orderRepository.findByUserAndStatus(optionalUser.get(), OrderStatus.Pending);
                order.setAmount(order.getAmount() + cart.getPrice());
                order.setTotalAmount(order.getTotalAmount() + cart.getPrice());
                order.getCartItems().add(cart);
                orderRepository.save(order);
                
                CartItemsDto productAddedToCartDto = new CartItemsDto();
                productAddedToCartDto.setId(updatedCart.getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(productAddedToCartDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }
}