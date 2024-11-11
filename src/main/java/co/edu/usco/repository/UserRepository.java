package co.edu.usco.repository;

import co.edu.usco.entity.User;
import co.edu.usco.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity.
 * Provides methods to perform CRUD operations and custom queries on User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds the first user by email.
     *
     * @param email the email of the user to find.
     * @return an Optional containing the found user, or empty if no user found.
     */
    Optional<User> findFirstByEmail(String email);

    /**
     * Finds a user by their role.
     *
     * @param role the role of the user to find.
     * @return the user with the specified role.
     */
    User findByRole(UserRole role);

    /**
     * Finds all users with the specified role.
     *
     * @param role the role of the users to find.
     * @return a list of users with the specified role.
     */
    List<User> findAllByRole(UserRole role);
}