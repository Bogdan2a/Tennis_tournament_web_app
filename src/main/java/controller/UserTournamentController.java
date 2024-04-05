package controller;

import model.RefereeSchedule;
import model.UserTournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserTournamentService;

import java.util.List;

@Controller
@RequestMapping("/api/user_tournaments")
public class UserTournamentController {

    @Autowired
    private UserTournamentService userTournamentService;

    @GetMapping
    public ResponseEntity<List<UserTournament>> getAllUserTournaments() {
        List<UserTournament> userTournaments = userTournamentService.getAllUserTournaments();
        return ResponseEntity.ok().body(userTournaments);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUserForTournament(@RequestParam Long userId, @RequestParam Long tournamentId) {
        // Check if the user is already registered for the tournament
        if (userTournamentService.isUserRegisteredForTournament(userId, tournamentId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is already registered for this tournament.");
        }

        // Register the user for the tournament
        userTournamentService.registerUserForTournament(userId, tournamentId);
        return ResponseEntity.ok().build();
    }


    // Other methods for user tournament management
}
