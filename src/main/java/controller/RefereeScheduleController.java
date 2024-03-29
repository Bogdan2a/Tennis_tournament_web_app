package controller;

import model.RefereeSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.RefereeScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/referee-schedules")
public class RefereeScheduleController {

    @Autowired
    private RefereeScheduleService refereeScheduleService;

    @GetMapping
    public ResponseEntity<List<RefereeSchedule>> getAllRefereeSchedules() {
        List<RefereeSchedule> refereeSchedules = refereeScheduleService.getAllRefereeSchedules();
        return ResponseEntity.ok().body(refereeSchedules);
    }

    // Other methods for referee schedule management
}
