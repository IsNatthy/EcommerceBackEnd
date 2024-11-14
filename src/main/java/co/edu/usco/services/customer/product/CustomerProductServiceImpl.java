package co.edu.usco.services.customer.product;

import co.edu.usco.dto.product.ProductDto;
import co.edu.usco.entity.Product;
import co.edu.usco.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for customer product-related operations.
 */
@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    private final ProductRepository productRepository;

    /**
     * Searches for products by their title.
     *
     * @param title the title to search for.
     * @return a list of ProductDto with product details that match the search criteria.
     */
    @Override
    public List<ProductDto> searchProductByTitle(String title) {
        return productRepository.findAllByNameContaining(title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    /**
     * Retrieves all products.
     *
     * @return a list of ProductDto with product details.
     */
    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(Product::getProductDto).collect(Collectors.toList());
    }

}