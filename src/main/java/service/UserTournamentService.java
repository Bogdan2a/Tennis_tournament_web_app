package service;

import model.UserTournament;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserTournamentService {
    List<UserTournament> getAllUserTournaments();
    // Other methods for user tournament management
}

