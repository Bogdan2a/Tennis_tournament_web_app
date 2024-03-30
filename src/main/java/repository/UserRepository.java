package repository;

import model.User;
import model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    String getUserRoleByUsername(String username);

    // You can add custom methods for specific queries if needed
    // For example: List<User> findByRole(UserRole role);
}

