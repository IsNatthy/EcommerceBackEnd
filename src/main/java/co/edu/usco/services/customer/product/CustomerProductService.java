package co.edu.usco.services.customer.product;

import co.edu.usco.dto.product.CompleteProductDetailDto;
import co.edu.usco.dto.product.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerProductService {
    List<ProductDto> searchProductByTitle(String title);

    List<ProductDto> getAllProducts();

    CompleteProductDetailDto getCompleteProductDetailById(Long productId);
}
