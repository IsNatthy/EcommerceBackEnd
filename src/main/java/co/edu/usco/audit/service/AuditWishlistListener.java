package co.edu.usco.audit.service;

import co.edu.usco.audit.entity.WishlistHistory;
import co.edu.usco.audit.repository.WishlistHistoryRepository;
import co.edu.usco.entity.Wishlist;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Listener class for auditing wishlist operations.
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class AuditWishlistListener {

    private final WishlistHistoryRepository wishlistHistoryRepository;

    /**
     * Method to be called before persisting a Wishlist entity.
     * Creates a new WishlistHistory entry with the current user and operation details.
     *
     * @param wishlist the Wishlist entity being persisted
     */
    @PrePersist
    private void prePersist(Wishlist wishlist){
        WishlistHistory history = new WishlistHistory();
        String user = getCurrentUser();
        history.setUser(user);
        history.setProductName(wishlist.getProduct().getName());
        history.setDate(LocalDateTime.now());
        history.setOperation("ADDED");
        this.wishlistHistoryRepository.save(history);
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