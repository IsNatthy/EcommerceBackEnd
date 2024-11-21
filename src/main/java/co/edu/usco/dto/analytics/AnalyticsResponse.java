package co.edu.usco.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A response DTO for analytics data.
 */
@Data
@AllArgsConstructor
public class AnalyticsResponse {
    /**
     * Number of orders in the current month.
     */
    private Long currentMonthOrders;

    /**
     * Number of orders in the previous month.
     */
    private Long previousMonthOrders;

    /**
     * Earnings in the current month.
     */
    private Long currentMonthEarnings;

    /**
     * Earnings in the previous month.
     */
    private Long previousMonthEarnings;

    /**
     * Number of orders placed.
     */
    private Long placed;

    /**
     * Number of orders shipped.
     */
    private Long shipped;

    /**
     * Number of orders delivered.
     */
    private Long delivered;
}