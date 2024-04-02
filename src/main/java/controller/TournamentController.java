package controller;

import model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.TournamentService;

import java.util.List;

@Controller
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        return ResponseEntity.ok().body(tournaments);
    }

    @PostMapping
    public ResponseEntity<?> createTournament(@RequestBody Tournament tournament) {
        // Validate tournament data
        tournamentService.createTournament(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Other methods for tournament management
}
