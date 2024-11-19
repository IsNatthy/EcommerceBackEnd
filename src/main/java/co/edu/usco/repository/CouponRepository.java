package co.edu.usco.repository;

import co.edu.usco.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Repository interface for managing Coupon entities.
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    /**
     * Checks if a coupon exists by code.
     *
     * @param code the code of the coupon.
     * @return true if a coupon with the specified code exists, false otherwise.
     */
    boolean existsByCode(String code);

    /**
     * Finds a Coupon entity by code.
     *
     * @param code the code of the coupon.
     * @return an Optional containing the found Coupon entity, or empty if not found.
     */
    Optional<Coupon> findByCode(String code);
}
