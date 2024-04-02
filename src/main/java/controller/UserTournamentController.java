package controller;

import model.UserTournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserTournamentService;

import java.util.List;

@Controller
@RequestMapping("/api/user-tournaments")
public class UserTournamentController {

    @Autowired
    private UserTournamentService userTournamentService;

    @GetMapping
    public ResponseEntity<List<UserTournament>> getAllUserTournaments() {
        List<UserTournament> userTournaments = userTournamentService.getAllUserTournaments();
        return ResponseEntity.ok().body(userTournaments);
    }

    // Other methods for user tournament management
}
