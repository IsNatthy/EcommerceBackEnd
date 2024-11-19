package co.edu.usco.repository;

import co.edu.usco.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing CartItems entities.
 */
@Repository
public interface CartRepository extends JpaRepository<CartItems, Long> {

    /**
     * Finds a CartItems entity by product ID, order ID, and user ID.
     *
     * @param productId the ID of the product.
     * @param orderId the ID of the order.
     * @param userId the ID of the user.
     * @return an Optional containing the found CartItems entity, or empty if not found.
     */
    Optional<CartItems> findByProductIdAndOrderIdAndUserId(Long productId, Long orderId, Long userId);

    /**
     * Finds a list of CartItems entities by order ID.
     *
     * @param orderId the ID of the order.
     * @return a list of CartItems entities that belong to the specified order.
     */
    List<CartItems> findByOrderId(Long orderId);
}