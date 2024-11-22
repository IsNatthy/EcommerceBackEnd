package co.edu.usco.audit.service;

import co.edu.usco.audit.entity.CouponHistory;
import co.edu.usco.audit.repository.CouponHistoryRepository;
import co.edu.usco.entity.Coupon;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Listener class for auditing coupon operations.
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class AuditCouponListener {

    private final CouponHistoryRepository couponHistoryRepository;

    /**
     * Method to be called before persisting a Coupon entity.
     * Creates a new CouponHistory entry with the current user and operation details.
     *
     * @param coupon the Coupon entity being persisted
     */
    @PrePersist
    private void prePersist(Coupon coupon){
        CouponHistory history = new CouponHistory();
        String user = getCurrentUser();
        history.setUser(user);
        history.setName(coupon.getName());
        history.setDate(LocalDateTime.now());
        history.setOperation("CREATE");
        this.couponHistoryRepository.save(history);
    }

    /**
     * Retrieves the current authenticated user.
     *
     * @return the username of the authenticated user, or "SYSTEM" if no user is authenticated
     */
    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null && authentication.isAuthenticated())
                ? authentication.getName()
                : "SYSTEM";
    }
}