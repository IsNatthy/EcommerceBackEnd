package co.edu.usco.audit.service;

import co.edu.usco.audit.entity.ProductHistory;
import co.edu.usco.audit.repository.ProductHistoryRepository;
import co.edu.usco.entity.Product;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Listener class for auditing product operations.
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class AuditProductListener {

    private final ProductHistoryRepository productHistoryRepository;

    /**
     * Method to be called before persisting a Product entity.
     * Creates a new ProductHistory entry with the current user and operation details.
     *
     * @param product the Product entity being persisted
     */
    @PrePersist
    private void prePersist(Product product){
        ProductHistory history = new ProductHistory();
        String user = getCurrentUser();
        history.setUser(user);
        history.setName(product.getName());
        history.setDate(LocalDateTime.now());
        history.setOperation("CREATE");
        this.productHistoryRepository.save(history);
    }

    /**
     * Method to be called before updating a Product entity.
     * Creates a new ProductHistory entry with the current user and operation details.
     *
     * @param product the Product entity being updated
     */
    @PreUpdate
    private void preUpdate(Product product){
        ProductHistory history = new ProductHistory();
        String user = getCurrentUser();
        history.setUser(user);
        history.setName(product.getName());
        history.setDate(LocalDateTime.now());
        history.setOperation("UPDATE");
        this.productHistoryRepository.save(history);
    }

    /**
     * Method to be called before removing a Product entity.
     * Creates a new ProductHistory entry with the current user and operation details.
     *
     * @param product the Product entity being removed
     */
    @PreRemove
    private void preRemove(Product product){
        ProductHistory history = new ProductHistory();
        String user = getCurrentUser();
        history.setUser(user);
        history.setName(product.getName());
        history.setDate(LocalDateTime.now());
        history.setOperation("DELETE");
        this.productHistoryRepository.save(history);
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