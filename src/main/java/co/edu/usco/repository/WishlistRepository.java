package co.edu.usco.repository;

import co.edu.usco.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Wishlist entities.
 */
@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    /**
     * Finds a list of Wishlist entities by user id.
     *
     * @param userId the id of the user.
     * @return a list of Wishlist entities that contain the specified user id.
     */
    List<Wishlist> findAllByUser_Id(Long userId);

    /**
     * Finds a Wishlist entity by user id and product id.
     *
     * @param userId the id of the user.
     * @param productId the id of the product.
     * @return a Wishlist entity that contains the specified user id and product id.
     */
    boolean existsByUserIdAndProductId(Long userId, Long productId);
}
