package service;

import model.Match;
import repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(Integer matchId) {
        return matchRepository.findById(Long.valueOf(matchId)).orElse(null);
    }
    // Implement other methods for match management
}

