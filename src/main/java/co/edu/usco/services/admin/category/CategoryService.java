package co.edu.usco.services.admin.category;

import co.edu.usco.dto.product.CategoryDto;
import co.edu.usco.entity.Category;

import java.util.List;

/**
 * Service interface for managing categories in the admin panel.
 */
public interface CategoryService {

    /**
     * Creates a new category.
     *
     * @param categoryDto the data transfer object containing the category details.
     * @return the created Category entity.
     */
    Category createCategory(CategoryDto categoryDto);

    /**
     * Retrieves all categories.
     *
     * @return a list of CategoryDto with category details.
     */
    List<CategoryDto> getAllCategories();
}