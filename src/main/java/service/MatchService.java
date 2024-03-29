package service;

import model.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    List<Match> getAllMatches();
    // Other methods for match management
}

