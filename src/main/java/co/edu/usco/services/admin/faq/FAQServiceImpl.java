package co.edu.usco.services.admin.faq;

import co.edu.usco.dto.faq.FAQDto;
import co.edu.usco.entity.FAQ;
import co.edu.usco.entity.Product;
import co.edu.usco.repository.FAQRepository;
import co.edu.usco.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for managing FAQs.
 */
@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepository faqRepository;

    private final ProductRepository productRepository;

    /**
     * Posts a new FAQ for a given product.
     *
     * @param productId the ID of the product to which the FAQ is related.
     * @param faqDto the data transfer object containing the FAQ details.
     * @return the created FAQDto, or null if the product is not found.
     */
    @Override
    public FAQDto postFAQ(Long productId, FAQDto faqDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            FAQ faq = new FAQ();
            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());
            FAQ createdFaq = faqRepository.save(faq);
            FAQDto createdFaqDto = new FAQDto();
            createdFaqDto.setId(createdFaq.getId());
            return createdFaqDto;
        }
        return null;
    }
}