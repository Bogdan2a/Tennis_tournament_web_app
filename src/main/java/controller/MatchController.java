package controller;

import model.Match;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.MatchService;

import java.util.List;

@Controller
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getAllMatches();
        return ResponseEntity.ok().body(matches);
    }



    @GetMapping("/get_match_by_id/{matchId}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long matchId) {
       // System.out.println("matchController");
        try {
            Match match = matchService.getMatchById(matchId);
           // System.out.println(match.getMatchDate());
           // System.out.println("intru aici");
            return ResponseEntity.ok(match);
        } catch (Exception e) {
          //  System.out.println("intru exception");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    // Other methods for match management
}
