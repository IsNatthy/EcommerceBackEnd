package co.edu.usco.services.customer.product;

import co.edu.usco.dto.product.CategoryDto;
import co.edu.usco.dto.product.CompleteProductDetailDto;
import co.edu.usco.dto.product.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing customer products.
 */
@Service
public interface CustomerProductService {

    /**
     * Searches for products by their title.
     *
     * @param title the title of the product to search for.
     * @return a list of ProductDto containing the products that match the title.
     */
    List<ProductDto> searchProductByTitle(String title);

    /**
     * Retrieves all products.
     *
     * @return a list of ProductDto containing all products.
     */
    List<ProductDto> getAllProducts();

    /**
     * Retrieves the complete details of a product by its ID.
     *
     * @param productId the ID of the product to retrieve details for.
     * @return the CompleteProductDetailDto containing the product details.
     */
    CompleteProductDetailDto getCompleteProductDetailById(Long productId);

    /**
     * Retrieves products by their category ID.
     *
     * @param categoryId the ID of the category to retrieve products for.
     * @return a list of ProductDto containing the products in the specified category.
     */
    List<ProductDto> getProductsByCategory(Long categoryId);

    /**
     * Retrieves all product categories.
     *
     * @return a list of CategoryDto containing all product categories.
     */
    List<CategoryDto> getAllCategories();
}