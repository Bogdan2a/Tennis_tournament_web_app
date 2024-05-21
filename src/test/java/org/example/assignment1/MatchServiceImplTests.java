package org.example.assignment1;

import model.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.MatchRepository;
import service.MatchServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MatchServiceImplTests {

    @InjectMocks
    private MatchServiceImpl matchService;

    @Mock
    private MatchRepository matchRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize all mocks
    }

    @Test
    public void testGetAllMatches() {
        // Given
        List<Match> matches = new ArrayList<>();
        matches.add(new Match());
        matches.add(new Match());

        when(matchRepository.findAll()).thenReturn(matches);

        // When
        List<Match> retrievedMatches = matchService.getAllMatches();

        // Then
        assertEquals(2, retrievedMatches.size());
        verify(matchRepository, times(1)).findAll();
    }

    @Test
    public void testGetMatchById() {
        // Given
        Long matchId = 1L;
        Match match = new Match();
        match.setId(matchId);

        when(matchRepository.findById(matchId)).thenReturn(Optional.of(match));

        // When
        Match retrievedMatch = matchService.getMatchById(matchId);

        // Then
        assertEquals(matchId, retrievedMatch.getId());
        verify(matchRepository, times(1)).findById(matchId);
    }

    @Test
    public void testUpdateMatchScore() {
        // Given
        Long userId = 1L;
        Long matchId = 1L;
        String score = "2-1";
        Match match = new Match();
        match.setId(matchId);

        when(matchRepository.findById(matchId)).thenReturn(Optional.of(match));

        // When
        matchService.updateMatchScore(userId, matchId, score);

        // Then
        assertEquals(score, match.getMatchScore());
        verify(matchRepository, times(1)).findById(matchId);
        verify(matchRepository, times(1)).save(match);
    }
}
