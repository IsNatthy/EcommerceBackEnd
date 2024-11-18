package co.edu.usco.services.admin.adminorder;

import co.edu.usco.dto.analytics.AnalyticsResponse;
import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.entity.Order;
import co.edu.usco.enums.OrderStatus;
import co.edu.usco.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service implementation for managing admin orders.
 */
@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {

    private final OrderRepository orderRepository;

    /**
     * Retrieves all placed orders.
     *
     * @return a list of OrderDto containing the details of the placed orders.
     */
    @Override
    public List<OrderDto> getAllPlacedOrders() {
        List<Order> orderList = orderRepository.findAllByStatusIn(List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered));
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

    /**
     * Changes the status of an order.
     *
     * @param orderId the ID of the order to change the status for.
     * @param status the new status to set for the order.
     * @return the updated OrderDto, or null if the order is not found.
     */
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

    /**
     * Calculates analytics data for orders.
     *
     * @return an AnalyticsResponse containing the analytics data.
     */
    public AnalyticsResponse calculateAnalytics() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusMonths(1);

        Long currentMonthOrders = getTotalOrdersForMonth(currentDate.getMonthValue(), currentDate.getYear());
        Long previousMonthOrders = getTotalOrdersForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

        Long currentMonthEarnings = getTotalEarningsForMonth(currentDate.getMonthValue(), currentDate.getYear());
        Long previousMonthEarnings = getTotalEarningsForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

        Long placed = orderRepository.countByStatus(OrderStatus.Placed);
        Long shipped = orderRepository.countByStatus(OrderStatus.Shipped);
        Long delivered = orderRepository.countByStatus(OrderStatus.Delivered);

        return new AnalyticsResponse(currentMonthOrders, previousMonthOrders, currentMonthEarnings, previousMonthEarnings, placed,
                shipped, delivered);
    }

    /**
     * Retrieves the total earnings for a specific month and year.
     *
     * @param month the month to retrieve earnings for.
     * @param year the year to retrieve earnings for.
     * @return the total earnings for the specified month and year.
     */
    public Long getTotalEarningsForMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startOfMonth = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endOfMonth = calendar.getTime();

        List<Order> orders = orderRepository.findByDateBetweenAndStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

        Long sum = 0L;
        for (Order order : orders) {
            sum += order.getAmount();
        }
        return sum;
    }

    /**
     * Retrieves the total number of orders for a specific month and year.
     *
     * @param month the month to retrieve orders for.
     * @param year the year to retrieve orders for.
     * @return the total number of orders for the specified month and year.
     */
    public Long getTotalOrdersForMonth(int month, int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startOfMonth = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endOfMonth = calendar.getTime();

        List<Order> orders = orderRepository.findByDateBetweenAndStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

        return (long) orders.size();
    }
}