package co.edu.usco.audit.repository;

import co.edu.usco.audit.entity.CouponHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Repository interface for accessing and managing CouponHistory entities.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
@Service
public interface CouponHistoryRepository extends JpaRepository<CouponHistory, Long> {
}