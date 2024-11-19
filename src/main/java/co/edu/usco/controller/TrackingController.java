package co.edu.usco.controller;

import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.services.customer.customerorder.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * REST controller for tracking orders.
 */
@RestController
@RequiredArgsConstructor
public class TrackingController {

    private final CustomerOrderService customerOrderService;

    /**
     * Searches for an order by its tracking ID.
     *
     * @param trackingId the tracking ID of the order.
     * @return a ResponseEntity containing the OrderDto if found, or a 404 Not Found status if not found.
     */
    @GetMapping("/order/{trackingId}")
    public ResponseEntity<OrderDto> searchOrderByTrackingId(@PathVariable UUID trackingId) {
        OrderDto orderDto = customerOrderService.searchOrderByTrackingId(trackingId);
        if (orderDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderDto);
    }
}