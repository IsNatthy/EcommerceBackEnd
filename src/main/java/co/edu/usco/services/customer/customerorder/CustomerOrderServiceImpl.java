package co.edu.usco.services.customer.customerorder;

import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.dto.order.PlaceOrderDto;
import co.edu.usco.entity.Order;
import co.edu.usco.entity.User;
import co.edu.usco.enums.OrderStatus;
import co.edu.usco.repository.OrderRepository;
import co.edu.usco.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service implementation for managing customer order operations.
 */
@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Places an order for a customer.
     *
     * @param placeOrderDto the DTO containing order details.
     * @return the created OrderDto or null if the user is not found.
     */
    @Override
    public OrderDto PlaceOrder(PlaceOrderDto placeOrderDto) {
        Order order = orderRepository.findByUserIdAndStatus(placeOrderDto.getUserId(), OrderStatus.Pending);
        Optional<User> optionalUser = userRepository.findById(placeOrderDto.getUserId());
        if (optionalUser.isPresent()) {
            order.setOrderDescription(placeOrderDto.getOrderDescription());
            order.setStatus(OrderStatus.Placed);
            order.setDate(new Date());
            order.setTrackingId(UUID.randomUUID());
            order.setAddress(placeOrderDto.getAddress());

            orderRepository.save(order);

            User user = optionalUser.get();
            Order newOrder = new Order();
            newOrder.setAmount(0L);
            newOrder.setTotalAmount(0L);
            newOrder.setDiscount(0L);
            newOrder.setUser(user);
            newOrder.setStatus(OrderStatus.Pending);
            orderRepository.save(newOrder);
            return order.getOrderDto();
        }
        return null;
    }

    /**
     * Retrieves the list of orders placed by a user.
     *
     * @param userId the ID of the user whose placed orders are to be retrieved.
     * @return a list of OrderDto containing the details of the placed orders.
     */
    @Override
    public List<OrderDto> getMyPlacedOrders(Long userId) {
        return orderRepository.findAllByUserIdAndStatusIn(userId, List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered)).stream().map(Order::getOrderDto).collect(Collectors.toList());
    }
}