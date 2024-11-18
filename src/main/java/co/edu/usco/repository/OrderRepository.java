package co.edu.usco.repository;

import co.edu.usco.entity.Order;
import co.edu.usco.entity.User;
import co.edu.usco.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    /**
     * Finds all Order entities by user ID and statuses in the provided list.
     *
     * @param userId the ID of the user associated with the orders.
     * @param orderStatusList the list of order statuses to filter by.
     * @return the list of found Order entities.
     */
    List<Order> findAllByUserIdAndStatusIn(Long userId, List<OrderStatus> orderStatusList);

    /**
     * Finds an Order entity by tracking ID.
     *
     * @param trackingId the tracking ID of the order.
     * @return an Optional containing the found Order entity, or empty if not found.
     */
    Optional<Order> findByTrackingId(UUID trackingId);

    @Query("SELECT COUNT(o) FROM Order o WHERE YEAR(o.date) = :year AND MONTH(o.date) = :month")
    Long countOrdersByMonthAndYear(int year, int month);

    @Query("SELECT SUM(o.amount) FROM Order o WHERE YEAR(o.date) = :year AND MONTH(o.date) = :month")
    BigDecimal sumEarningsByMonthAndYear(int year, int month);

    List<Order> findByDateBetweenAndStatus(Date startOfMonth, Date endOfMonth, OrderStatus status);

    Long countByStatus(OrderStatus status);

}