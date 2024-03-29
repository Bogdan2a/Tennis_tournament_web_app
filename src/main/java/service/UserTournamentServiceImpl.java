package service;

import model.UserTournament;
import repository.UserTournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTournamentServiceImpl implements UserTournamentService {

    @Autowired
    private UserTournamentRepository userTournamentRepository;

    @Override
    public List<UserTournament> getAllUserTournaments() {
        return userTournamentRepository.findAll();
    }

    // Implement other methods for user tournament management
}
