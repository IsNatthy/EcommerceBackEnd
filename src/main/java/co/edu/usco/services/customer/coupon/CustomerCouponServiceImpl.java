package co.edu.usco.services.customer.coupon;

import co.edu.usco.dto.order.OrderDto;
import co.edu.usco.entity.Coupon;
import co.edu.usco.entity.Order;
import co.edu.usco.enums.OrderStatus;
import co.edu.usco.exceptions.ValidationException;
import co.edu.usco.repository.CouponRepository;
import co.edu.usco.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service implementation for managing customer coupon operations.
 */
@Service
@RequiredArgsConstructor
public class CustomerCouponServiceImpl implements CustomerCouponService {

    private final CouponRepository couponRepository;

    private final OrderRepository orderRepository;

    /**
     * Applies a coupon to a customer's order.
     *
     * @param userId the ID of the user.
     * @param code the coupon code.
     * @return the updated OrderDto with the applied coupon.
     * @throws ValidationException if the coupon is not found or has expired.
     */
    public OrderDto applyCoupon(Long userId, String code) {
        Order order = orderRepository.findByUserIdAndStatus(userId, OrderStatus.Pending);
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new ValidationException("Coupon not found."));

        if (couponIsExpired(coupon)) {
            throw new ValidationException("Coupon has expired.");
        }
        double discountAmount = ((coupon.getDiscount() / 100.0) * order.getTotalAmount());
        double netAmount = order.getTotalAmount() - discountAmount;

        order.setAmount((long) netAmount);
        order.setDiscount((long) discountAmount);
        order.setCoupon(coupon);

        orderRepository.save(order);
        return order.getOrderDto();
    }

    /**
     * Checks if a coupon is expired.
     *
     * @param coupon the Coupon entity to check.
     * @return true if the coupon is expired, false otherwise.
     */
    private boolean couponIsExpired(Coupon coupon) {
        Date currentDate = new Date();
        Date expirationDate = coupon.getExpirationDate();
        return expirationDate != null && currentDate.after(expirationDate);
    }
}