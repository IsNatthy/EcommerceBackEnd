package co.edu.usco.controller.admin;

import co.edu.usco.services.admin.faq.FAQService;
import lombok.RequiredArgsConstructor;
import co.edu.usco.dto.faq.FAQDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing FAQ entries in the admin panel.
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminFAQController {

        private final FAQService faqService;

        /**
         * Creates a new FAQ entry for a specific product.
         *
         * @param productId the ID of the product to associate the FAQ with.
         * @param faqDto the data transfer object containing the FAQ details.
         * @return a ResponseEntity containing the created FAQDto and a 201 Created status.
         */
        @PostMapping("/faq/{productId}")
        public ResponseEntity<FAQDto> postFAQ(@PathVariable Long productId, @RequestBody FAQDto faqDto) {
            FAQDto createdFaqDto = faqService.postFAQ(productId, faqDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFaqDto);
        }
}
