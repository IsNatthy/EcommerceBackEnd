package co.edu.usco.dto.order;

import lombok.Data;

@Data
public class PlaceOrderDto {

    private Long userId;

    private String address;

    private String orderDescription;

}
