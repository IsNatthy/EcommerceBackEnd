package co.edu.usco.repository;

import co.edu.usco.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing FAQ entities.
 */
@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {

    /**
     * Finds a list of FAQ entities by product id.
     *
     * @param productId the id of the product.
     * @return a list of FAQ entities that contain the specified product id.
     */
    List<FAQ> findAllByProductId(Long productId);
}
