package co.edu.usco.dto.product;

import co.edu.usco.dto.faq.FAQDto;
import co.edu.usco.dto.review.ReviewDto;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object for Complete Product Detail.
 */
@Data
public class CompleteProductDetailDto {

    /**
     * The product details.
     */
    private ProductDto productDto;

    /**
     * The list of frequently asked questions related to the product.
     */
    private List<FAQDto> faqDtoList;

    /**
     * The list of reviews for the product.
     */
    private List<ReviewDto> reviewDtoList;
}