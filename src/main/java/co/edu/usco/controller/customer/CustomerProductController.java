package co.edu.usco.controller.customer;

import co.edu.usco.dto.product.ProductDto;
import co.edu.usco.services.customer.product.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing customer product-related operations.
 */
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;

    /**
     * Retrieves all products.
     *
     * @return a ResponseEntity containing a list of ProductDto with product details.
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = customerProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    /**
     * Searches for products by their title.
     *
     * @param title the title to search for.
     * @return a ResponseEntity containing a list of ProductDto with product details that match the search criteria.
     */
    @GetMapping("/search/{title}")
    public ResponseEntity<List<ProductDto>> searchProductByTitle(@PathVariable("title") String title) {
        List<ProductDto> productDtos = customerProductService.searchProductByTitle(title);
        return ResponseEntity.ok(productDtos);
    }

}