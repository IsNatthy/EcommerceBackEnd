package co.edu.usco.services.customer.coupon;

import co.edu.usco.dto.order.OrderDto;

public interface CustomerCouponService {
    OrderDto applyCoupon(Long userId, String code);
}
