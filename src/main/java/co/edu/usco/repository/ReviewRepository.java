package co.edu.usco.repository;

import co.edu.usco.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Reviews entities.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long> {

    /**
     * Finds a list of Reviews entities by product id.
     *
     * @param productId the id of the product.
     * @return a list of Reviews entities that contain the specified product id.
     */
    List<Reviews> findAllByProductId(Long productId);
}
