package co.edu.usco.repository;

import co.edu.usco.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findAllByProductId(Long productId);
}
