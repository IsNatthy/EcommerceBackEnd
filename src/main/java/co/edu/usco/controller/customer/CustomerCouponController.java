package co.edu.usco.controller.customer;

import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.exceptions.ValidationException;
import co.edu.usco.services.customer.coupon.CustomerCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing customer coupon operations.
 */
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerCouponController {

    private final CustomerCouponService customerCouponService;

    /**
     * Applies a coupon to a customer's order.
     *
     * @param userId the ID of the user.
     * @param code the coupon code.
     * @return a ResponseEntity containing the updated OrderDto with the applied coupon or an error message.
     */
    @GetMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code) {
        try {
            OrderDto orderDto = customerCouponService.applyCoupon(userId, code);
            return ResponseEntity.ok(orderDto);
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}