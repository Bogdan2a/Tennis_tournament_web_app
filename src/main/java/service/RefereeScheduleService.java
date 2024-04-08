package service;

import model.RefereeSchedule;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RefereeScheduleService {


    List<RefereeSchedule> getRefereeSchedulesByRefereeId(Long refereeId);
    // Other methods for referee schedule management
}
