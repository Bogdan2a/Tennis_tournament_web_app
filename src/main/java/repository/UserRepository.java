package repository;

import model.User;
import model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u.role FROM User u WHERE u.username = :username")
    String getUserRoleByUsername(String username);

    // You can add custom methods for specific queries if needed
    // For example: List<User> findByRole(UserRole role);
}

