package co.edu.usco.services.customer.review;

import co.edu.usco.dto.order.OrderedProductDetails;
import co.edu.usco.dto.order.OrderedProductsResponseDto;
import co.edu.usco.entity.CartItems;
import co.edu.usco.entity.Order;
import co.edu.usco.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}