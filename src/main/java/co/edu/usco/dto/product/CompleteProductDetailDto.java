package co.edu.usco.dto.product;

import co.edu.usco.dto.faq.FAQDto;
import co.edu.usco.dto.review.ReviewDto;
import lombok.Data;

import java.util.List;

@Data
public class CompleteProductDetailDto {

    private ProductDto productDto;

    private List<FAQDto> faqDtoList;

    private List<ReviewDto> reviewDtoList;
}
