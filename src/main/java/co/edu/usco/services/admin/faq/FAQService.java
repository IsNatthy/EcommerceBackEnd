package co.edu.usco.services.admin.faq;

import co.edu.usco.dto.faq.FAQDto;

/**
 * Service interface for managing FAQs in the admin panel.
 */
public interface FAQService {

    /**
     * Posts a new FAQ for a specific product.
     *
     * @param productId the ID of the product to post the FAQ for.
     * @param faqDto the data transfer object containing the FAQ details.
     * @return the posted FAQDto with FAQ details.
     */
    FAQDto postFAQ(Long productId, FAQDto faqDto);
}