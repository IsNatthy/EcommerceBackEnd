package co.edu.usco.controller.admin;

import co.edu.usco.dto.product.CategoryDto;
import co.edu.usco.entity.Category;
import co.edu.usco.services.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing categories in the admin panel.
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    /**
     * Endpoint to create a new category.
     *
     * @param categoryDto the data transfer object containing category details.
     * @return a ResponseEntity containing the created Category and HTTP status CREATED.
     */
    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    /**
     * Endpoint to retrieve all categories.
     *
     * @return a ResponseEntity containing a list of CategoryDto and HTTP status OK.
     */
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDtos);
    }
}