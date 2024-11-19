package co.edu.usco.services.admin.adminorder;

import co.edu.usco.dto.analytics.AnalyticsResponse;
import co.edu.usco.dto.order.OrderDto;

import java.util.List;

/**
 * Service interface for managing orders in the admin panel.
 */
public interface AdminOrderService {

    /**
     * Returns a list of all placed orders.
     *
     * @return a list of all placed orders.
     */
    List<OrderDto> getAllPlacedOrders();

    /**
     * Changes the status of an order.
     *
     * @param orderId the ID of the order to change the status for.
     * @param status the new status to set for the order.
     * @return the updated OrderDto, or null if the order is not found.
     */
    OrderDto changeOrderStatus(Long orderId, String status);

    /**
     * Calculates the analytics for the orders.
     *
     * @return the analytics response containing the calculated analytics.
     */
    AnalyticsResponse calculateAnalytics();

}
