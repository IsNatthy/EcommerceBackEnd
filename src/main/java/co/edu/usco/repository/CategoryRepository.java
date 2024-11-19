package co.edu.usco.repository;

import co.edu.usco.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Category entities.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Finds a list of Category entities by name.
     *
     * @param title the name of the category.
     * @return a list of Category entities that contain the specified name.
     */
    List<Category> findAllByNameContaining(String title);
}
