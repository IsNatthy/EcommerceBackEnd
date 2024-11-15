package co.edu.usco.services.admin.coupon;

import co.edu.usco.entity.Coupon;

import java.util.List;

/**
 * Interface for managing admin coupon operations.
 */
public interface AdminCouponService {

    /**
     * Creates a new coupon.
     *
     * @param coupon the Coupon entity to be created.
     * @return the created Coupon entity.
     */
    Coupon createCoupon(Coupon coupon);

    /**
     * Retrieves all coupons from the database.
     *
     * @return a list of all Coupon entities.
     */
    List<Coupon> getAllCoupons();
}