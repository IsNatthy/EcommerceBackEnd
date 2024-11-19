package co.edu.usco.services.customer.product;

import co.edu.usco.dto.product.CategoryDto;
import co.edu.usco.dto.product.CompleteProductDetailDto;
import co.edu.usco.dto.product.ProductDto;
import co.edu.usco.entity.Category;
import co.edu.usco.entity.FAQ;
import co.edu.usco.entity.Product;
import co.edu.usco.entity.Reviews;
import co.edu.usco.repository.CategoryRepository;
import co.edu.usco.repository.FAQRepository;
import co.edu.usco.repository.ProductRepository;
import co.edu.usco.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for customer product-related operations.
 */
@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    private final ProductRepository productRepository;

    private final FAQRepository faqRepository;

    private final ReviewRepository reviewRepository;

    private final CategoryRepository categoryRepository;

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

    /**
     * Retrieves all categories.
     *
     * @return a list of CategoryDto with category details.
     */
    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    /**
     * Retrieves products by category ID.
     *
     * @param categoryId the ID of the category to retrieve products for.
     * @return a list of ProductDto with product details that belong to the specified category.
     */
    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    /**
     * Retrieves the complete product details by product ID.
     *
     * @param productId the ID of the product to retrieve details for.
     * @return a CompleteProductDetailDto containing product details, FAQs, and reviews, or null if the product is not found.
     */
    @Override
    public CompleteProductDetailDto getCompleteProductDetailById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        List<FAQ> faqList = faqRepository.findAllByProductId(productId);
        List<Reviews> reviewsList = reviewRepository.findAllByProductId(productId);
        if (optionalProduct.isPresent()) {
            CompleteProductDetailDto completeProductDetailDto = new CompleteProductDetailDto();
            completeProductDetailDto.setProductDto(optionalProduct.get().getProductDto());
            completeProductDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
            completeProductDetailDto.setReviewDtoList(reviewsList.stream().map(Reviews::getReviewDto).collect(Collectors.toList()));
            return completeProductDetailDto;
        }
        return null;
    }
}