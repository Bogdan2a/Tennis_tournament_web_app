package service;

import model.Tournament;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TournamentService {
    List<Tournament> getAllTournaments();
    void createTournament(Tournament tournament);
    // Other methods for tournament management
}

