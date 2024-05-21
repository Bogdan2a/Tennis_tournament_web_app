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
    public Match getMatchById(Long matchId) {
        return matchRepository.findById(matchId).orElse(null);
    }

    @Override
    public void updateMatchScore(Long userId, Long matchId, String score) {

        Match match = matchRepository.findById(matchId).orElse(null);

        match.setMatchScore(score);

        matchRepository.save(match);
    }
}

