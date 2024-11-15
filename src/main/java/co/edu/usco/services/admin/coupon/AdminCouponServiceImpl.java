package co.edu.usco.services.admin.coupon;

import co.edu.usco.entity.Coupon;
import co.edu.usco.exceptions.ValidationException;
import co.edu.usco.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing admin coupon operations.
 */
@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService{

    private final CouponRepository couponRepository;

    /**
     * Creates a new coupon.
     *
     * @param coupon the Coupon entity to be created.
     * @return the created Coupon entity.
     * @throws ValidationException if the coupon code already exists.
     */
    public Coupon createCoupon(Coupon coupon) {
        // Check if the coupon code already exists in the database
        if (couponRepository.existsByCode(coupon.getCode())) {
            throw new ValidationException("Coupon code already exists.");
        }

        return couponRepository.save(coupon);
    }

    /**
     * Retrieves all coupons from the database.
     *
     * @return a list of all Coupon entities.
     */
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
}