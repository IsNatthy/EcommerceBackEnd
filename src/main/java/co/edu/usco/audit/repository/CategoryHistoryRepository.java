package co.edu.usco.audit.repository;

import co.edu.usco.audit.entity.CategoryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing CategoryHistory entities.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
@Repository
public interface CategoryHistoryRepository extends JpaRepository<CategoryHistory, Long> {
}