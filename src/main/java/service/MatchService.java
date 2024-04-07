package service;

import model.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    List<Match> getAllMatches();

    Match getMatchById(Long matchId);

    void updateMatchScore(Long userId, Long matchId, String score);
    // Other methods for match management
}

