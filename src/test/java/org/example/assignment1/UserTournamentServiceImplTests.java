package org.example.assignment1;

import model.UserTournament;
import model.UserTournamentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.UserTournamentRepository;
import service.UserTournamentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserTournamentServiceImplTests {

    @InjectMocks
    private UserTournamentServiceImpl userTournamentService;

    @Mock
    private UserTournamentRepository userTournamentRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize all mocks
    }

    @Test
    public void testGetAllUserTournaments() {
        // Given
        List<UserTournament> userTournaments = new ArrayList<>();
        userTournaments.add(new UserTournament());
        userTournaments.add(new UserTournament());

        when(userTournamentRepository.findAll()).thenReturn(userTournaments);

        // When
        List<UserTournament> retrievedUserTournaments = userTournamentService.getAllUserTournaments();

        // Then
        assertEquals(2, retrievedUserTournaments.size());
        verify(userTournamentRepository, times(1)).findAll();
    }

    @Test
    public void testRegisterUserForTournament() {
        // Given
        Long userId = 1L;
        Long tournamentId = 1L;

        // When
        userTournamentService.registerUserForTournament(userId, tournamentId);

        // Then
        verify(userTournamentRepository, times(1)).save(any(UserTournament.class));
    }

    @Test
    public void testIsUserRegisteredForTournament() {
        // Given
        Long userId = 1L;
        Long tournamentId = 1L;
        UserTournament userTournament = new UserTournament();

        when(userTournamentRepository.findByUserIdAndTournamentId(userId, tournamentId)).thenReturn(userTournament);

        // When
        boolean isRegistered = userTournamentService.isUserRegisteredForTournament(userId, tournamentId);

        // Then
        assertTrue(isRegistered);
        verify(userTournamentRepository, times(1)).findByUserIdAndTournamentId(userId, tournamentId);
    }

    @Test
    public void testAcceptTournamentRegistration() {
        // Given
        Long userTournamentId = 1L;
        UserTournament userTournament = new UserTournament();
        userTournament.setId(userTournamentId);

        when(userTournamentRepository.findById(userTournamentId)).thenReturn(Optional.of(userTournament));

        // When
        userTournamentService.acceptTournamentRegistration(userTournamentId);

        // Then
        assertEquals(UserTournamentStatus.ACCEPTED, userTournament.getStatus());
        verify(userTournamentRepository, times(1)).save(userTournament);
    }

    @Test
    public void testRejectTournamentRegistration() {
        // Given
        Long userTournamentId = 1L;
        UserTournament userTournament = new UserTournament();
        userTournament.setId(userTournamentId);

        when(userTournamentRepository.findById(userTournamentId)).thenReturn(Optional.of(userTournament));

        // When
        userTournamentService.rejectTournamentRegistration(userTournamentId);

        // Then
        assertEquals(UserTournamentStatus.REJECTED, userTournament.getStatus());
        verify(userTournamentRepository, times(1)).save(userTournament);
    }

    @Test
    public void testGetUserTournamentById() {
        // Given
        Long userTournamentId = 1L;
        UserTournament userTournament = new UserTournament();
        userTournament.setId(userTournamentId);

        when(userTournamentRepository.findById(userTournamentId)).thenReturn(Optional.of(userTournament));

        // When
        UserTournament retrievedUserTournament = userTournamentService.getUserTournamentById(userTournamentId);

        // Then
        assertNotNull(retrievedUserTournament);
        assertEquals(userTournamentId, retrievedUserTournament.getId());
        verify(userTournamentRepository, times(1)).findById(userTournamentId);
    }
}
