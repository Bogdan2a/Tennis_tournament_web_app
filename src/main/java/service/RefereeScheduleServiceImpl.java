package service;

import model.RefereeSchedule;
import repository.RefereeScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefereeScheduleServiceImpl implements RefereeScheduleService {

    @Autowired
    private RefereeScheduleRepository refereeScheduleRepository;


    @Override
    public List<RefereeSchedule> getRefereeSchedulesByRefereeId(Long refereeId) {
        return refereeScheduleRepository.findByRefereeId(refereeId);
    }
    // Implement other methods for referee schedule management
}
