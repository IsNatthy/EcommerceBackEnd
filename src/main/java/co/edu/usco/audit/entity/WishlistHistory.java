package co.edu.usco.audit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity class representing the history of operations performed on wishlists.
 */
@Entity
@Data
@Table(name = "wishlist_history")
public class WishlistHistory {

    /**
     * Unique identifier for the wishlist history record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product associated with the wishlist.
     */
    private String productName;

    /**
     * Operation performed on the wishlist (e.g., CREATE, UPDATE, DELETE).
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