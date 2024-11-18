package co.edu.usco.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderedProductsResponseDto {
    private List<OrderedProductDetails> orderedProductDetailsList;

    private Long orderAmount;
}
