package repository;

import model.UserTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTournamentRepository extends JpaRepository<UserTournament, Long> {
    // You can add custom methods for specific queries if needed
}

