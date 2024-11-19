package co.edu.usco.repository;

import co.edu.usco.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Repository interface for managing Product entities.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds all Product entities with names containing the specified title.
     *
     * @param title the title to search for.
     * @return a list of Product entities with names containing the specified title.
     */
    List<Product> findAllByNameContaining(String title);

    /**
     * Finds all Product entities by category ID.
     *
     * @param categoryId the ID of the category.
     * @return a list of Product entities that belong to the specified category.
     */
    List<Product> findAllByCategoryId(Long categoryId);
}