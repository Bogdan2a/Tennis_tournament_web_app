package repository;

import model.UserTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTournamentRepository extends JpaRepository<UserTournament, Long> {
    UserTournament findByUserIdAndTournamentId(Long userId, Long tournamentId);
    @Query("SELECT ut FROM UserTournament ut WHERE ut.status = 'PENDING'")
    List<UserTournament> findAllPendingRegistrations();
}

