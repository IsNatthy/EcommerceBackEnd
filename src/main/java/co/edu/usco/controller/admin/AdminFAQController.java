package co.edu.usco.controller.admin;

import co.edu.usco.services.admin.faq.FAQService;
import lombok.RequiredArgsConstructor;
import co.edu.usco.dto.faq.FAQDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminFAQController {

        private final FAQService faqService;

        @PostMapping("/faq/{productId}")
        public ResponseEntity<FAQDto> postFAQ(@PathVariable Long productId, @RequestBody FAQDto faqDto) {
            FAQDto createdFaqDto = faqService.postFAQ(productId, faqDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFaqDto);
        }
}
