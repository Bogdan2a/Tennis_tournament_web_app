package service;

import model.UserTournament;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserTournamentService {
    List<UserTournament> getAllUserTournaments();

    void registerUserForTournament(Long userId, Long tournamentId);

    boolean isUserRegisteredForTournament(Long userId, Long tournamentId);
    // Other methods for user tournament management
}

