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

    @GetMapping("/referee_own_program.html")
    public String showRefereeOwnProgram(@RequestParam Long userId, Model model) {

        List<RefereeSchedule> refereeSchedules = refereeScheduleService.getRefereeSchedulesByRefereeId(userId);
        model.addAttribute("refereeSchedules", refereeSchedules);
        if(SecurityVariable.isUserLoggedIn && SecurityVariable.isUserReferee)
            return "referee_own_program";
        else
            return "redirect:/api/users/not_logged_in.html";
    }

}
