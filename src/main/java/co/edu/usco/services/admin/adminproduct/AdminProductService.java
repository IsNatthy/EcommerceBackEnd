package co.edu.usco.services.admin.adminproduct;

import co.edu.usco.dto.product.ProductDto;
import co.edu.usco.dto.product.SecondProductDto;
import co.edu.usco.entity.Product;

import java.io.IOException;
import java.util.List;

/**
 * Service interface for managing products in the admin panel.
 */
public interface AdminProductService {

    /**
     * Adds a new product.
     *
     * @param secondProductDto the data transfer object containing the product details.
     * @return the added Product entity.
     * @throws IOException if an input or output exception occurred.
     */
    Product addProduct(SecondProductDto secondProductDto) throws IOException;

    /**
     * Retrieves all products.
     *
     * @return a list of ProductDto with product details.
     */
    List<ProductDto> getAllProducts();

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product to retrieve.
     * @return the ProductDto with product details.
     */
    ProductDto getProductById(Long productId);

    /**
     * Searches for products by their title.
     *
     * @param title the title of the product to search for.
     * @return a list of ProductDto with product details that match the search criteria.
     */
    List<ProductDto> searchProductByTitle(String title);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     * @return true if the product was successfully deleted, false otherwise.
     */
    boolean deleteProduct(Long id);

    /**
     * Updates a product.
     *
     * @param productId the ID of the product to update.
     * @param productDto the data transfer object containing the updated product details.
     * @return the updated ProductDto.
     * @throws IOException if an input or output exception occurred.
     */
    ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException;
}