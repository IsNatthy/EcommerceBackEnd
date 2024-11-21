package co.edu.usco.dto.order;

import lombok.Data;

/**
 * Data Transfer Object for placing an order.
 */
@Data
public class PlaceOrderDto {

    /**
     * The unique identifier of the user placing the order.
     */
    private Long userId;

    /**
     * The address where the order is to be delivered.
     */
    private String address;

    /**
     * The description of the order.
     */
    private String orderDescription;

}