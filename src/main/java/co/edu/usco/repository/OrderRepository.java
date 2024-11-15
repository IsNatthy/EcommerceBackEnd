package co.edu.usco.repository;

import co.edu.usco.entity.Order;
import co.edu.usco.entity.User;
import co.edu.usco.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Order entities.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds an Order entity by user and status.
     *
     * @param user the user associated with the order.
     * @param status the status of the order.
     * @return the found Order entity.
     */
    Order findByUserAndStatus(User user, OrderStatus status);

    /**
     * Finds an Order entity by user ID and status.
     *
     * @param userId the ID of the user associated with the order.
     * @param status the status of the order.
     * @return the found Order entity.
     */
    Order findByUserIdAndStatus(Long userId, OrderStatus status);
}