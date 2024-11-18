package co.edu.usco.services.customer.review;

import co.edu.usco.dto.order.OrderedProductsResponseDto;
import co.edu.usco.dto.review.ReviewDto;

import java.io.IOException;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
