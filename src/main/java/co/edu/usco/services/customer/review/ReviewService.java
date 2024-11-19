package co.edu.usco.services.customer.review;

import co.edu.usco.dto.order.OrderedProductsResponseDto;
import co.edu.usco.dto.review.ReviewDto;

import java.io.IOException;

/**
 * Service interface for managing customer reviews.
 */
public interface ReviewService {

    /**
     * Retrieves the details of ordered products by order ID.
     *
     * @param orderId the ID of the order to retrieve product details for.
     * @return the OrderedProductsResponseDto containing the ordered product details.
     */
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);

    /**
     * Submits a review for a product.
     *
     * @param reviewDto the data transfer object containing the review details.
     * @return the submitted ReviewDto with review details.
     * @throws IOException if an input or output exception occurred.
     */
    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}