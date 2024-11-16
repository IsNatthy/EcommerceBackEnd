package co.edu.usco.repository;

import co.edu.usco.entity.Order;
import co.edu.usco.entity.User;
import co.edu.usco.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * Finds all Order entities with statuses in the provided list.
     *
     * @param orderStatusList the list of order statuses to filter by.
     * @return the list of found Order entities.
     */
    List<Order> findAllByStatusIn(List<OrderStatus> orderStatusList);

    List<Order> findAllByUserIdAndStatusIn(Long userId, List<OrderStatus> orderStatusList);

}