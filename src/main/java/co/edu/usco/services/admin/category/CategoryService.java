package co.edu.usco.services.admin.category;

import co.edu.usco.dto.CategoryDto;
import co.edu.usco.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();
}
