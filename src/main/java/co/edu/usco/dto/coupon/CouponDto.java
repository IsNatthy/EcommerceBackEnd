package co.edu.usco.dto.coupon;

import lombok.Data;

import java.util.Date;

/**
 * Data Transfer Object for Coupon.
 */
@Data
public class CouponDto {

    /**
     * Unique identifier for the coupon.
     */
    private Long id;

    /**
     * Name of the coupon.
     */
    private String name;

    /**
     * Code of the coupon.
     */
    private String code;

    /**
     * Discount value of the coupon.
     */
    private Long discount;

    /**
     * Expiration date of the coupon.
     */
    private Date expirationDate;
}