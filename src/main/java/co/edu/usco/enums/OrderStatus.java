package co.edu.usco.enums;

/**
 * Enum representing the status of an order.
 */
public enum OrderStatus {
    /**
     * Order has been created but not yet processed.
     */
    Pending,

    /**
     * Order has been placed and is being processed.
     */
    Placed,

    /**
     * Order has been shipped to the customer.
     */
    Shipped,

    /**
     * Order has been delivered to the customer.
     */
    Delivered
}