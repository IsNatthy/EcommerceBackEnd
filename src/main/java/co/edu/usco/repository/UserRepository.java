package co.edu.usco.repository;

import co.edu.usco.entity.UserEntity;
import co.edu.usco.enums.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for UserEntity.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds the first user by email.
     *
     * @param email the email to search for.
     * @return an Optional containing the found UserEntity, or empty if not found.
     */
    Optional<UserEntity> findFirstByEmail(String email);

    /**
     * Finds a user by role.
     *
     * @param userRole the role to search for.
     * @return the UserEntity with the specified role.
     */
    UserEntity findByRole(UserRole userRole);
}