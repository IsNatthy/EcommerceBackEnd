package co.edu.usco.services.admin.adminorder;

import co.edu.usco.dto.order.OrderDto;

import java.util.List;

public interface AdminOrderService {
    List<OrderDto> getAllPlacedOrders();
    OrderDto changeOrderStatus(Long orderId, String status);
}
