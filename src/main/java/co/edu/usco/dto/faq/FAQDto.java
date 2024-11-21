package co.edu.usco.dto.faq;

import lombok.Data;

/**
 * Data Transfer Object for FAQ (Frequently Asked Questions).
 */
@Data
public class FAQDto {
    /**
     * The unique identifier of the FAQ.
     */
    private Long id;

    /**
     * The question of the FAQ.
     */
    private String question;

    /**
     * The answer to the FAQ.
     */
    private String answer;

    /**
     * The unique identifier of the product associated with the FAQ.
     */
    private Long productId;

    /**
     * The name of the product associated with the FAQ.
     */
    private String productName;
}