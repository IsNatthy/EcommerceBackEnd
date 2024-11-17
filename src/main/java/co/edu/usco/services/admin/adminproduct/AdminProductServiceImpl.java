package co.edu.usco.services.admin.adminproduct;

import co.edu.usco.dto.product.ProductDto;
import co.edu.usco.dto.product.SecondProductDto;
import co.edu.usco.entity.Category;
import co.edu.usco.entity.Product;
import co.edu.usco.repository.CategoryRepository;
import co.edu.usco.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing products in the admin panel.
 */
@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Adds a new product to the repository.
     *
     * @param secondProductDto the data transfer object containing product details.
     * @return the saved Product entity.
     * @throws IOException if an I/O error occurs while processing the product image.
     */
    @Override
    public Product addProduct(SecondProductDto secondProductDto) throws IOException {
        Product product = new Product();
        product.setName(secondProductDto.getName());
        product.setPrice(secondProductDto.getPrice());
        product.setDescription(secondProductDto.getDescription());
        product.setImg(secondProductDto.getImg().getBytes());
        Category category = categoryRepository.findById(Long.parseLong(secondProductDto.getCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    /**
     * Retrieves all products from the repository.
     *
     * @return a list of ProductDto containing product details.
     */
    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setCategoryId(product.getCategory().getId());
            productDto.setCategoryName(product.getCategory().getName());
            productDto.setReturnedImg(product.getImg());
            return productDto;
        }).collect(Collectors.toList());
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product to retrieve.
     * @return the ProductDto containing product details, or null if the product is not found.
     */
    @Override
    public ProductDto getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get().getProductDto();
        } else {
            return null;
        }
    }

    /**
     * Searches for products by their title.
     *
     * @param title the title to search for.
     * @return a list of ProductDto containing product details that match the search criteria.
     */
    @Override
    public List<ProductDto> searchProductByTitle(String title) {
        List<Product> products = productRepository.findAllByNameContaining(title);
        return products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setCategoryId(product.getCategory().getId());
            productDto.setCategoryName(product.getCategory().getName());
            productDto.setReturnedImg(product.getImg());
            return productDto;
        }).collect(Collectors.toList());
    }

    /**
     * Updates a product by its ID.
     *
     * @param productId the ID of the product to update.
     * @param productDto the data transfer object containing updated product details.
     * @return the updated ProductDto, or null if the product or category is not found.
     * @throws IOException if an I/O error occurs while processing the product image.
     */
    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        if (optionalProduct.isPresent() && category.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            if (productDto.getImg() != null) {
                product.setImg(productDto.getImg().getBytes());
            }
            product.setCategory(category.get());
            return productRepository.save(product).getProductDto();
        } else {
            return null;
        }
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     * @return true if the product was successfully deleted, false if the product was not found.
     */
    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}