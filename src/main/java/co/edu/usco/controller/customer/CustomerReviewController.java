package co.edu.usco.controller.customer;

import co.edu.usco.dto.order.OrderedProductsResponseDto;
import co.edu.usco.dto.review.ReviewDto;
import co.edu.usco.services.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerReviewController {

    private final ReviewService reviewService;

    /**
     * Retrieves the details of ordered products by order ID.
     *
     * @param orderId the ID of the order to retrieve details for.
     * @return a ResponseEntity containing the OrderedProductsResponseDto with the details of the ordered products.
     */
    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductsResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId) {
        OrderedProductsResponseDto orderedProductsResponseDto = reviewService.getOrderedProductsDetailsByOrderId(orderId);
        return ResponseEntity.ok(orderedProductsResponseDto);
    }

    /**
     * Submits a review for a product.
     *
     * @param reviewDto the data transfer object containing the review details.
     * @return a ResponseEntity containing the created ReviewDto or an error message if the review submission fails.
     * @throws IOException if an I/O error occurs during the review submission.
     */
    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException {
        ReviewDto reviewedDto = reviewService.giveReview(reviewDto);
        if (reviewedDto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewedDto);
    }

}