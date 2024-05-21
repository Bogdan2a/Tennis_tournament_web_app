package service;

import model.Tournament;
import repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.TournamentService;

import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }



}

