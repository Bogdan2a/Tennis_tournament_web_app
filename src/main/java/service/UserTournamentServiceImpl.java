package service;

import model.UserTournament;
import model.UserTournamentStatus;
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
        userTournament.setStatus(UserTournamentStatus.PENDING);
        userTournamentRepository.save(userTournament);
    }

    @Override
    public boolean isUserRegisteredForTournament(Long userId, Long tournamentId) {
        UserTournament userTournament = userTournamentRepository.findByUserIdAndTournamentId(userId, tournamentId);
        return userTournament != null;
    }

    @Override
    public void acceptTournamentRegistration(Long userTournamentId) {
        // Find the user tournament by ID
        UserTournament userTournament = userTournamentRepository.findById(userTournamentId).orElse(null);
        if (userTournament != null) {
            userTournament.setStatus(UserTournamentStatus.ACCEPTED);
            userTournamentRepository.save(userTournament);
        }
    }

    @Override
    public void rejectTournamentRegistration(Long userTournamentId) {
        // Find the user tournament by ID
        UserTournament userTournament = userTournamentRepository.findById(userTournamentId).orElse(null);
        if (userTournament != null) {
            userTournament.setStatus(UserTournamentStatus.REJECTED);
            userTournamentRepository.save(userTournament);
        }
    }

    @Override
    public UserTournament getUserTournamentById(Long userTournamentId) {
        return userTournamentRepository.findById(userTournamentId).orElse(null);
    }

}
