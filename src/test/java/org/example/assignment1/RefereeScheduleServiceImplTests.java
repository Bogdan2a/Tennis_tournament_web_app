package org.example.assignment1;

import model.RefereeSchedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.RefereeScheduleRepository;
import service.RefereeScheduleServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RefereeScheduleServiceImplTests {

    @InjectMocks
    private RefereeScheduleServiceImpl refereeScheduleService;

    @Mock
    private RefereeScheduleRepository refereeScheduleRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize all mocks
    }

    @Test
    public void testGetRefereeSchedulesByRefereeId() {
        // Given
        Long refereeId = 1L;
        List<RefereeSchedule> schedules = new ArrayList<>();
        schedules.add(new RefereeSchedule());
        schedules.add(new RefereeSchedule());

        when(refereeScheduleRepository.findByRefereeId(refereeId)).thenReturn(schedules);

        // When
        List<RefereeSchedule> retrievedSchedules = refereeScheduleService.getRefereeSchedulesByRefereeId(refereeId);

        // Then
        assertEquals(2, retrievedSchedules.size());
        verify(refereeScheduleRepository, times(1)).findByRefereeId(refereeId);
    }
}
