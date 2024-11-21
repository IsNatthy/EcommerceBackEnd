package co.edu.usco.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * Entity class representing a Coupon.
 */
@Entity
@Data
@Table(name = "coupons")
public class Coupon {

    /**
     * Unique identifier for the coupon.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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