package co.edu.usco.audit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity class representing the history of product operations.
 */
@Entity
@Data
@Table(name = "product_history")
public class ProductHistory {

    /**
     * Unique identifier for the product history entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product.
     */
    private String name;

    /**
     * Operation performed on the product (e.g., CREATE, UPDATE, DELETE).
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