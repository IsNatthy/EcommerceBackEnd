package co.edu.usco.audit.repository;

import co.edu.usco.audit.entity.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing ProductHistory entities.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {
}