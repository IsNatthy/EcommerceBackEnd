package co.edu.usco.repository;

import co.edu.usco.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    boolean existsByCode(String code);
    Optional<Coupon> findByCode(String code);
}
