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

    @Override
    public void registerUserForTournament(Long userId, Long tournamentId) {
        UserTournament userTournament = new UserTournament();
        userTournament.setUserId(userId);
        userTournament.setTournamentId(tournamentId);
        userTournamentRepository.save(userTournament);
    }

    @Override
    public boolean isUserRegisteredForTournament(Long userId, Long tournamentId) {
        UserTournament userTournament = userTournamentRepository.findByUserIdAndTournamentId(userId, tournamentId);
        return userTournament != null;
    }

}
