package controller;

import model.Match;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/match_schedule_and_score.html")
    public String showAdminViewUsers(Model model) {
        //fetch all matches
        List<Match> matches = matchService.getAllMatches();
        model.addAttribute("matches", matches);
        return "match_schedule_and_score";
    }

    @GetMapping("/manage_match_score.html")
    public String showManageScore(Model model, @RequestParam(name = "userId") String userId) {
        List<Match> matches = matchService.getAllMatches();
        model.addAttribute("userPasat", userId);

        // Pass matches data to the template
        model.addAttribute("matches", matches);
        return "manage_match_score";
    }

    @PostMapping("/update_score")
    public ResponseEntity<String> updateMatchScore(@RequestParam Long userId, @RequestParam Long matchId, @RequestParam String score) {
        try {
            // Call the service method to update the match score
            matchService.updateMatchScore(userId, matchId, score);
            return ResponseEntity.ok("Match score updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update match score: " + e.getMessage());
        }
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

    @GetMapping("/admin_export_match_info.html")
    public String showAdminExportInfo(Model model) {
        //fetch all matches
        List<Match> matches = matchService.getAllMatches();
        for (Match match : matches) {
            System.out.println(match.getId());
            System.out.println(match.getPlayer1Id());
            System.out.println(match.getPlayer2Id());
            System.out.println();

        }
        model.addAttribute("matches", matches);
        return "admin_export_match_info";
    }
    // Other methods for match management
}
