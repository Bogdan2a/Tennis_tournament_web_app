package repository;

import model.RefereeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefereeScheduleRepository extends JpaRepository<RefereeSchedule, Long> {
    // You can add custom methods for specific queries if needed
}

