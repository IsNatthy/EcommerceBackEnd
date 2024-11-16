package co.edu.usco.services.admin.adminorder;

import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.entity.Order;
import co.edu.usco.enums.OrderStatus;
import co.edu.usco.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDto> getAllPlacedOrders() {
        List<Order> orderList = orderRepository.findAllByStatusIn(List.of(OrderStatus.Placed,OrderStatus.Shipped,OrderStatus.Delivered));
        return orderList.stream().map(order -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setOrderDescription(order.getOrderDescription());
            orderDto.setDate(order.getDate());
            orderDto.setTrackingId(order.getTrackingId());
            orderDto.setAmount(order.getAmount());
            orderDto.setPayment(order.getPayment());
            orderDto.setAddress(order.getAddress());
            orderDto.setStatus(order.getStatus());
            orderDto.setUserName(order.getUser().getName());
            return orderDto;
        }).collect(Collectors.toList());
    }

    @Override
    public OrderDto changeOrderStatus(Long orderId, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (Objects.equals(status, "Shipped")) {
                order.setStatus(OrderStatus.Shipped);
            } else {
                order.setStatus(OrderStatus.Delivered);
            }
            Order order1 = orderRepository.save(order);
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order1.getId());
            return orderDto;
        }
        return null;
    }
}