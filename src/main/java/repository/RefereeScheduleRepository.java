package repository;

import model.RefereeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefereeScheduleRepository extends JpaRepository<RefereeSchedule, Long> {
    List<RefereeSchedule> findByRefereeId(Long refereeId);
    // You can add custom methods for specific queries if needed
}

