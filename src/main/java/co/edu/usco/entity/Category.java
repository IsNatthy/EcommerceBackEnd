package co.edu.usco.entity;

import co.edu.usco.audit.service.AuditCategoryListener;
import co.edu.usco.dto.product.CategoryDto;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing a Category.
 */
@Entity
@Data
@Table(name = "category")
@EntityListeners(AuditCategoryListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    /**
     * Updates the Category entity with data from the given CategoryDto.
     *
     * @param categoryDto the CategoryDto containing the data to update
     */
    public void getCategoryEntity(CategoryDto categoryDto) {
        this.name = categoryDto.getName();
        this.description = categoryDto.getDescription();
    }

    /**
     * Converts the Category entity to a CategoryDto.
     *
     * @return the CategoryDto containing the data from the Category entity
     */
    public CategoryDto getCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(id);
        categoryDto.setName(name);
        categoryDto.setDescription(description);
        return categoryDto;
    }
}