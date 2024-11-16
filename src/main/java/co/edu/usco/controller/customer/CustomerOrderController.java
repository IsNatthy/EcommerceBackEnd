package co.edu.usco.controller.customer;

import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.dto.order.PlaceOrderDto;
import co.edu.usco.services.customer.customerorder.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing customer order operations.
 */
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    /**
     * Places an order for a customer.
     *
     * @param placeOrderDto the DTO containing order details.
     * @return a ResponseEntity containing the created OrderDto.
     */
    @PostMapping("/placeOrder")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
        OrderDto OrderDto = customerOrderService.PlaceOrder(placeOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderDto);
    }

    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable Long userId) {
        List<OrderDto> orderDtos = customerOrderService.getMyPlacedOrders(userId);
        return ResponseEntity.ok(orderDtos);
    }

}