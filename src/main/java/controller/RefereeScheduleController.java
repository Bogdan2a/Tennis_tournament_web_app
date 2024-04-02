package controller;

import model.RefereeSchedule;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.RefereeScheduleService;

import java.sql.Ref;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/referee-schedules")
public class RefereeScheduleController {

    @Autowired
    private RefereeScheduleService refereeScheduleService;

    /*@GetMapping
    public ResponseEntity<List<RefereeSchedule>> getAllRefereeSchedules() {
        List<RefereeSchedule> refereeSchedules = refereeScheduleService.getAllRefereeSchedules();
        return ResponseEntity.ok().body(refereeSchedules);
    }*/

    @GetMapping("/referee_own_program.html")
    public String showRefereeOwnProgram(@RequestParam Long userId, Model model) {

        List<RefereeSchedule> refereeSchedules = refereeScheduleService.getRefereeSchedulesByRefereeId(userId);
       // List<RefereeSchedule> refereeSchedules = refereeScheduleService.getAllRefereeSchedules();
       /* for (RefereeSchedule refereeSchedule : refereeSchedules) {
            System.out.println(refereeSchedule.getId());
            System.out.println("postpone that question");
            System.out.println(refereeSchedule.getMatchId());
        }*/
       // System.out.println("test test");
        model.addAttribute("refereeSchedules", refereeSchedules);
        return "referee_own_program";
    }

    /*@PutMapping("/update_account/{userId}")
    public ResponseEntity<String> addRefereeSchedule(@RequestBody RefereeSchedule refereeSchedule, @PathVariable String userId) {
        try {
            refereeScheduleService.addRefereeSchedule(refereeSchedule);
            return ResponseEntity.ok("Referee schedule added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add referee schedule: " + e.getMessage());
        }
    }*/

    // Other methods for referee schedule management
}
