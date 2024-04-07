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
        System.out.println(matchRepository.findAll().get(0).getPlayer1Id());
        System.out.println(matchRepository.getPlayer1IdbyMatchId(matchRepository.findAll().get(0).getId()));
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(Long matchId) {
        return matchRepository.findById(matchId).orElse(null);
    }
    // Implement other methods for match management
    @Override
    public void updateMatchScore(Long userId, Long matchId, String score) {
        // Step 1: Find the match by its ID
        Match match = matchRepository.findById(matchId).orElse(null);

        // Step 2: Check if the logged-in user has the authority to update the match score
        // You can implement your authorization logic here

        // Step 3: Update the match score with the new value provided in the score parameter
        match.setMatchScore(score);

        // Save the updated match
        matchRepository.save(match);
    }
}

