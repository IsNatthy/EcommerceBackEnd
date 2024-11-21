package co.edu.usco.dto.order;

import lombok.Data;

import java.util.Date;

/**
 * Data Transfer Object for Response Order.
 */
@Data
public class ResponseOrderDto {

    /**
     * The total amount of the order.
     */
    private float amount;

    /**
     * The date when the order was placed.
     */
    private Date date;

    /**
     * The description of the order.
     */
    private String OrderDescription;

    /**
     * The unique identifier of the order.
     */
    private Long orderId;

}