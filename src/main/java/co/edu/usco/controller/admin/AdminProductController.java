package co.edu.usco.controller.admin;

import co.edu.usco.dto.product.ProductDto;
import co.edu.usco.dto.product.SecondProductDto;
import co.edu.usco.entity.Product;
import co.edu.usco.services.admin.adminproduct.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * REST controller for managing products in the admin panel.
 */
@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    /**
     * Adds a new product.
     *
     * @param secondProductDto the data transfer object containing product details.
     * @return the created Product entity.
     * @throws IOException if an I/O error occurs while processing the product image.
     */
    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@ModelAttribute SecondProductDto secondProductDto) throws IOException {
        Product product = adminProductService.addProduct(secondProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    /**
     * Retrieves all products.
     *
     * @return a list of ProductDto containing product details.
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = adminProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product to retrieve.
     * @return the ProductDto containing product details, or a 404 status if the product is not found.
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto productDto = adminProductService.getProductById(productId);
        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Searches for products by their title.
     *
     * @param title the title to search for.
     * @return a list of ProductDto containing product details that match the search criteria.
     */
    @GetMapping("/search/{title}")
    public ResponseEntity<List<ProductDto>> searchProductByTitle(@PathVariable("title") String title) {
        List<ProductDto> productDtos = adminProductService.searchProductByTitle(title);
        return ResponseEntity.ok(productDtos);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to delete.
     * @return a ResponseEntity with no content if the deletion is successful.
     */
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        adminProductService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete.
     * @return a ResponseEntity with no content if the deletion is successful, or a 404 status if the entity is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        boolean deleted = adminProductService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}