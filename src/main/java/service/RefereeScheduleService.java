package service;

import model.RefereeSchedule;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RefereeScheduleService {
    List<RefereeSchedule> getAllRefereeSchedules();
    // Other methods for referee schedule management
}
