package co.edu.usco.services.admin.category;

import co.edu.usco.dto.product.CategoryDto;
import co.edu.usco.entity.Category;
import co.edu.usco.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing categories in the admin panel.
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Creates a new category.
     *
     * @param categoryDto the data transfer object containing category details.
     * @return the saved Category entity.
     */
    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return categoryRepository.save(category);
    }

    /**
     * Retrieves all categories from the repository.
     *
     * @return a list of CategoryDto containing category details.
     */
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());
            return categoryDto;
        }).collect(Collectors.toList());
    }
}