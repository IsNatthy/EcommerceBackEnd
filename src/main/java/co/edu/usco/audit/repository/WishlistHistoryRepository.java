package co.edu.usco.audit.repository;

import co.edu.usco.audit.entity.WishlistHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for WishlistHistory entities.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface WishlistHistoryRepository extends JpaRepository<WishlistHistory, Long> {
}