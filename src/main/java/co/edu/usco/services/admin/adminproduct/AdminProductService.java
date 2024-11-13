package co.edu.usco.services.admin.adminproduct;

import co.edu.usco.dto.product.ProductDto;
import co.edu.usco.dto.product.SecondProductDto;
import co.edu.usco.entity.Product;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    Product addProduct(SecondProductDto secondProductDto) throws IOException;

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long productId);

}
