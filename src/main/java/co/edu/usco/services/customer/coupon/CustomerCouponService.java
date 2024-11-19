package co.edu.usco.services.customer.coupon;

import co.edu.usco.dto.order.OrderDto;

/**
 * Service interface for managing customer coupons.
 */
public interface CustomerCouponService {

    /**
     * Applies a coupon to an order for a specific user.
     *
     * @param userId the ID of the user to apply the coupon for.
     * @param code the coupon code to apply.
     * @return the OrderDto containing the updated order details after applying the coupon.
     */
    OrderDto applyCoupon(Long userId, String code);
}