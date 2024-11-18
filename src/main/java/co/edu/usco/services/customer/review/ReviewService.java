package co.edu.usco.services.customer.review;

import co.edu.usco.dto.order.OrderedProductsResponseDto;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
}
