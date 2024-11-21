package co.edu.usco.entity;

import co.edu.usco.dto.product.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entity class representing a product.
 */
@Entity
@Data
@Table(name = "product")
public class Product {

    /**
     * Unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product.
     */
    private String name;

    /**
     * Price of the product.
     */
    private Long price;

    /**
     * Description of the product.
     */
    @Lob
    @Column(name = "description")
    private String description;

    /**
     * Image of the product in byte array format.
     */
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    /**
     * Category to which the product belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    /**
     * Converts the Product entity to a ProductDto.
     *
     * @return a ProductDto object containing the product details.
     */
    public ProductDto getProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setDescription(description);
        productDto.setReturnedImg(img);
        productDto.setCategoryId(category.getId());
        productDto.setCategoryName(category.getName());
        return productDto;
    }
}