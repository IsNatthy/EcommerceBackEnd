package co.edu.usco.controller.customer;

import co.edu.usco.dto.order.OrderedProductsResponseDto;
import co.edu.usco.services.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerReviewController {

    private final ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductsResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId) {
        OrderedProductsResponseDto orderedProductsResponseDto = reviewService.getOrderedProductsDetailsByOrderId(orderId);
        return ResponseEntity.ok(orderedProductsResponseDto);
    }

}
