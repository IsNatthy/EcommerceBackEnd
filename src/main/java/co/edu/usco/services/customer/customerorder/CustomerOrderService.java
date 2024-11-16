package co.edu.usco.services.customer.customerorder;

import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.dto.order.PlaceOrderDto;

import java.util.List;

public interface CustomerOrderService {
    OrderDto PlaceOrder(PlaceOrderDto placeOrderDto);

    List<OrderDto> getMyPlacedOrders(Long userId);

}
