package co.edu.usco.audit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity class representing the history of category changes.
 */
@Entity
@Data
@Table(name = "category_history")
public class CategoryHistory {

    /**
     * Unique identifier for the category history record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category.
     */
    private String name;

    /**
     * Operation performed on the category (e.g., CREATE, UPDATE, DELETE).
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