package co.edu.usco.audit.service;

import co.edu.usco.audit.entity.CategoryHistory;
import co.edu.usco.audit.repository.CategoryHistoryRepository;
import co.edu.usco.entity.Category;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Listener class for auditing category operations.
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class AuditCategoryListener {

    private final CategoryHistoryRepository categoryHistoryRepository;

    /**
     * Method to be called before persisting a Category entity.
     * Creates a new CategoryHistory entry with the current user and operation details.
     *
     * @param category the Category entity being persisted
     */
    @PrePersist
    private void prePersist(Category category){
        CategoryHistory history = new CategoryHistory();
        String user = getCurrentUser();
        history.setUser(user);
        history.setName(category.getName());
        history.setDate(LocalDateTime.now());
        history.setOperation("CREATE");
        this.categoryHistoryRepository.save(history);
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