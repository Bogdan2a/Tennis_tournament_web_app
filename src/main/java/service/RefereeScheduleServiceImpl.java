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
    public List<RefereeSchedule> getAllRefereeSchedules() {
        return refereeScheduleRepository.findAll();
    }

    // Implement other methods for referee schedule management
}
