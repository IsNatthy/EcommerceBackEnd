package co.edu.usco.dto.order;

import co.edu.usco.dto.cart.CartItemsDto;
import co.edu.usco.enums.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for Order.
 */
@Data
public class OrderDto {
    /**
     * The description of the order.
     */
    private String orderDescription;

    /**
     * The list of items in the cart associated with the order.
     */
    private List<CartItemsDto> cartItems;

    /**
     * The unique identifier of the order.
     */
    private Long id;

    /**
     * The date when the order was placed.
     */
    private Date date;

    /**
     * The tracking ID of the order.
     */
    private UUID trackingId;

    /**
     * The total amount of the order.
     */
    private Long amount;

    /**
     * The address where the order is to be delivered.
     */
    private String address;

    /**
     * The status of the order.
     */
    private OrderStatus status;

    /**
     * The payment method used for the order.
     */
    private String payment;

    /**
     * The name of the user who placed the order.
     */
    private String userName;

    /**
     * The total amount of the order including all charges.
     */
    private Long totalAmount;

    /**
     * The discount applied to the order.
     */
    private Long discount;

    /**
     * The name of the coupon applied to the order.
     */
    private String couponName;
}