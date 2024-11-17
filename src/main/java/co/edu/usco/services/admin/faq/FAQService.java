package co.edu.usco.services.admin.faq;

import co.edu.usco.dto.faq.FAQDto;

public interface FAQService {
    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
