package co.edu.usco.services.customer.review;

import co.edu.usco.dto.order.OrderedProductDetails;
import co.edu.usco.dto.order.OrderedProductsResponseDto;
import co.edu.usco.dto.review.ReviewDto;
import co.edu.usco.entity.*;
import co.edu.usco.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing customer reviews.
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final CartRepository cartRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    /**
     * Retrieves the details of ordered products by order ID.
     *
     * @param orderId the ID of the order to retrieve details for.
     * @return an OrderedProductsResponseDto containing the details of the ordered products.
     */
    @Override
    public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId) {
        List<CartItems> cartItems = cartRepository.findByOrderId(orderId);
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();
        if (optionalOrder.isPresent()) {
            Long orderAmount = optionalOrder.get().getAmount();
            orderedProductsResponseDto.setOrderAmount(orderAmount);
        }
        if (!cartItems.isEmpty()) {
            List<OrderedProductDetails> orderedProductDetailsList = new ArrayList<>();
            for (CartItems cartItem : cartItems) {
                OrderedProductDetails productDetails = new OrderedProductDetails();
                productDetails.setId(cartItem.getProduct().getId());
                productDetails.setName(cartItem.getProduct().getName());
                productDetails.setProductPrice(cartItem.getPrice());
                productDetails.setQuantity(cartItem.getQuantity());
                productDetails.setReturnedImg(cartItem.getProduct().getImg());
                orderedProductDetailsList.add(productDetails);
            }
            orderedProductsResponseDto.setOrderedProductDetailsList(orderedProductDetailsList);
        }
        return orderedProductsResponseDto;
    }

    /**
     * Adds a review for a product.
     *
     * @param reviewDto the review data transfer object containing review details.
     * @return the saved review data transfer object or null if the product or user is not found.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            Reviews review = new Reviews();
            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setImg(reviewDto.getImg().getBytes());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            Reviews reviewed = reviewRepository.save(review);
            ReviewDto reviewedDto = new ReviewDto();
            reviewedDto.setId(reviewed.getId());
            return reviewedDto;
        }
        return null;
    }
}