package controller;

import model.RefereeSchedule;
import model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/tournament_registration.html")
    public String showTournamentRegistration(@RequestParam Long userId, Model model) {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        //System.out.println(tournaments);
        model.addAttribute("tournaments", tournaments);
        if(SecurityVariable.isUserLoggedIn && SecurityVariable.isUserPlayer)
            return "tournament_registration";
        else
            return "redirect:/api/users/not_logged_in.html";
    }
}
