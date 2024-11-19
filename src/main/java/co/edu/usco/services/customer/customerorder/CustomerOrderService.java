package co.edu.usco.services.customer.customerorder;

import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.dto.order.PlaceOrderDto;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing customer orders.
 */
public interface CustomerOrderService {

    /**
     * Places a new order based on the provided order details.
     *
     * @param placeOrderDto the data transfer object containing the order details.
     * @return the placed OrderDto with order details.
     */
    OrderDto PlaceOrder(PlaceOrderDto placeOrderDto);

    /**
     * Retrieves the list of orders placed by a specific user.
     *
     * @param userId the ID of the user whose orders are to be retrieved.
     * @return the list of OrderDto containing the user's placed orders.
     */
    List<OrderDto> getMyPlacedOrders(Long userId);

    /**
     * Searches for an order by its tracking ID.
     *
     * @param trackingId the tracking ID of the order to search for.
     * @return the OrderDto containing the order details.
     */
    OrderDto searchOrderByTrackingId(UUID trackingId);
}