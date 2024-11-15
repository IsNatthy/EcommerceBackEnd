package co.edu.usco.controller.admin;

import co.edu.usco.entity.Coupon;
import co.edu.usco.services.admin.coupon.AdminCouponService;
import co.edu.usco.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing admin coupon operations.
 */
@RestController
@RequestMapping("/api/admin/coupons")
@RequiredArgsConstructor
public class AdminCouponController {

    private final AdminCouponService adminCouponService;

    /**
     * Creates a new coupon.
     *
     * @param coupon the Coupon entity to be created.
     * @return a ResponseEntity containing the created Coupon entity or an error message.
     */
    @PostMapping
    public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon) {
        try {
            Coupon createdCoupon = adminCouponService.createCoupon(coupon);
            return ResponseEntity.ok(createdCoupon);
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    /**
     * Retrieves all coupons from the database.
     *
     * @return a ResponseEntity containing a list of all Coupon entities.
     */
    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> coupons = adminCouponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }
}