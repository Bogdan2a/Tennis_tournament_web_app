package service;

import model.UserTournament;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserTournamentService {
    List<UserTournament> getAllUserTournaments();

    void registerUserForTournament(Long userId, Long tournamentId);

    boolean isUserRegisteredForTournament(Long userId, Long tournamentId);

    void acceptTournamentRegistration(Long userTournamentId);

    void rejectTournamentRegistration(Long userTournamentId);

    UserTournament getUserTournamentById(Long userTournamentId);
}

