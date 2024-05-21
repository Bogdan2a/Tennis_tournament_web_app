package org.example.assignment1;

import model.Tournament;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.TournamentRepository;
import service.TournamentServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TournamentServiceImplTests {

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    @Mock
    private TournamentRepository tournamentRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize all mocks
    }

    @Test
    public void testGetAllTournaments() {
        // Given
        List<Tournament> tournaments = new ArrayList<>();
        tournaments.add(new Tournament());
        tournaments.add(new Tournament());

        when(tournamentRepository.findAll()).thenReturn(tournaments);

        // When
        List<Tournament> retrievedTournaments = tournamentService.getAllTournaments();

        // Then
        assertEquals(2, retrievedTournaments.size());
        verify(tournamentRepository, times(1)).findAll();
    }
}
