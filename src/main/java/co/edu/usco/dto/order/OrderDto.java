package co.edu.usco.dto.order;

import co.edu.usco.dto.cart.CartItemsDto;
import co.edu.usco.enums.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private String orderDescription;

    private List<CartItemsDto> cartItems;

    private Long id;

    private Date date;

    private UUID trackingId;

    private Long amount;

    private String address;

    private OrderStatus status;

    private String payment;

    private String userName;

    private Long totalAmount;
}