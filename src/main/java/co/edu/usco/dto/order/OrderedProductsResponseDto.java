package co.edu.usco.dto.order;

import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object for Ordered Products Response.
 */
@Data
public class OrderedProductsResponseDto {
    /**
     * The list of ordered product details.
     */
    private List<OrderedProductDetails> orderedProductDetailsList;

    /**
     * The total amount of the order.
     */
    private Long orderAmount;
}