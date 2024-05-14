package controller;

import model.RefereeSchedule;
import model.UserRole;
import model.UserTournament;
import model.UserTournamentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.EmailService;
import service.UserService;
import service.UserTournamentService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/user_tournaments")
public class UserTournamentController {

    @Autowired
    private UserTournamentService userTournamentService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<UserTournament>> getAllUserTournaments() {
        List<UserTournament> userTournaments = userTournamentService.getAllUserTournaments();
        return ResponseEntity.ok().body(userTournaments);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUserForTournament(@RequestParam Long userId, @RequestParam Long tournamentId) {

        if (userTournamentService.isUserRegisteredForTournament(userId, tournamentId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is already registered for this tournament.");
        }

        userTournamentService.registerUserForTournament(userId, tournamentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin_registration_approval.html")
    public String getAdminRegistrationApprovalPage(Model model) {
        List<UserTournament> userTournaments = userTournamentService.getAllUserTournaments();

        UserTournamentStatus status = UserTournamentStatus.PENDING;
        userTournaments = userTournaments.stream()
                .filter(userTournament -> status.equals(userTournament.getStatus()))
                .collect(Collectors.toList());

        System.out.println(userTournaments.size());
        model.addAttribute("userTournaments", userTournaments);
        return "admin_registration_approval";
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptTournamentRegistration(@RequestParam Long userTournamentId) {
        userTournamentService.acceptTournamentRegistration(userTournamentId);
        //here email the user, fetch the user email by the userTournamentId.getUserId()
        // Fetch the user's email using user ID
        Long userId = userTournamentService.getUserTournamentById(userTournamentId).getUserId();
        String userEmail = userService.getUserEmailById(userId);

        // Prepare email content
        String subject = "Tournament Registration Accepted";
        String body = "Your tournament registration has been accepted. Congratulations!";

        // Send email
        emailService.sendEmail(userEmail, subject, body);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/accept.html")
    public String showAcceptPage(Model model) {
        return "accept";
    }

    @GetMapping("/reject.html")
    public String showRejectPage(Model model) {
        return "reject";
    }

    @PostMapping("/reject")
    public ResponseEntity<?> rejectTournamentRegistration(@RequestParam Long userTournamentId) {
        userTournamentService.rejectTournamentRegistration(userTournamentId);

        // Fetch the user's email using user ID
        Long userId = userTournamentService.getUserTournamentById(userTournamentId).getUserId();
        String userEmail = userService.getUserEmailById(userId);

        // Prepare email content
        String subject = "Tournament Registration Rejected";
        String body = "Unfortunately, your tournament registration has been rejected.";

        // Send email
        emailService.sendEmail(userEmail, subject, body);

        return ResponseEntity.ok().build();
    }


}
