package co.edu.usco.entity;

import co.edu.usco.dto.faq.FAQDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entity class representing a Frequently Asked Question (FAQ).
 */
@Data
@Entity
@Table(name = "faq")
public class FAQ {

    /**
     * Unique identifier for the FAQ.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * The product associated with the FAQ.
     * This relationship is many-to-one and uses lazy fetching.
     * The product is deleted if the associated FAQ is deleted.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    /**
     * Converts the FAQ entity to a FAQDto.
     *
     * @return the FAQDto representation of the FAQ entity.
     */
    public FAQDto getFAQDto() {
        FAQDto faqDto = new FAQDto();
        faqDto.setId(id);
        faqDto.setQuestion(question);
        faqDto.setAnswer(question);
        faqDto.setProductId(product.getId());
        faqDto.setProductName(product.getName());
        return faqDto;
    }
}