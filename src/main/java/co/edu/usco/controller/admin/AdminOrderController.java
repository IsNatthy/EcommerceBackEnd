package co.edu.usco.controller.admin;

import co.edu.usco.dto.analytics.AnalyticsResponse;
import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.services.admin.adminorder.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing admin orders.
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    /**
     * Retrieves all placed orders.
     *
     * @return a ResponseEntity containing a list of OrderDto objects representing the placed orders.
     */
    @GetMapping("/placedOrders")
    public ResponseEntity<List<OrderDto>> getAllPlacedOrders() {
        List<OrderDto> allPlacedOrdersDtos = adminOrderService.getAllPlacedOrders();
        return ResponseEntity.ok(allPlacedOrdersDtos);
    }

    /**
     * Changes the status of an order.
     *
     * @param orderId the ID of the order to change the status for.
     * @param status the new status to set for the order.
     * @return a ResponseEntity containing the updated OrderDto if successful, or a BAD_REQUEST status if something went wrong.
     */
    @GetMapping("/order/{orderId}/{status}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
        OrderDto orderDto = adminOrderService.changeOrderStatus(orderId, status);
        if (orderDto == null)
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    /**
     * Retrieves analytics data for orders.
     *
     * @return a ResponseEntity containing an AnalyticsResponse object with the analytics data.
     */
    @GetMapping("/order/analytics")
    public ResponseEntity<AnalyticsResponse> getAnalytics() {
        AnalyticsResponse response = adminOrderService.calculateAnalytics();
        return ResponseEntity.ok(response);
    }
}