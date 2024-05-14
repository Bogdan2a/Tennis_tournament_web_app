package model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Table(name = "user_tournaments")
public class UserTournament {

    @jakarta.persistence.Id
    @Id
    @Column(name = "user_tournament_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    private Long userId;

    @JoinColumn(name = "tournament_id")
    private Long tournamentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserTournamentStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public UserTournamentStatus getStatus() {
        return status;
    }

    public void setStatus(UserTournamentStatus status) {
        this.status = status;
    }
}
