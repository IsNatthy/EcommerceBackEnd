package co.edu.usco.audit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity class representing the history of coupon operations.
 */
@Entity
@Data
@Table(name = "coupon_history")
public class CouponHistory {
    /**
     * Unique identifier for the coupon history record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the coupon.
     */
    private String name;

    /**
     * Operation performed on the coupon (e.g., created, updated, deleted).
     */
    private String operation;

    /**
     * User who performed the operation.
     */
    private String user;

    /**
     * Date and time when the operation was performed.
     */
    private LocalDateTime date;
}