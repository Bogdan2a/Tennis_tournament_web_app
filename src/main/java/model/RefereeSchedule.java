package model;

import jakarta.persistence.*;
import java.util.Date;
import repository.MatchRepository;

@Entity
@Table(name = "referee_schedule")
public class RefereeSchedule {
    @Id
    @Column(name = "referee_schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "referee_id")
    @JoinColumn(name = "referee_id")
    private int refereeId;


    @Column(name = "match_id")
    @JoinColumn(name = "match_id")
    private int matchId;

    // Constructors, getters, setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(int refereeId) {
        this.refereeId = refereeId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }


/* public User getReferee() {
        return referee;
    }

    public void setReferee(User referee) {
        this.referee = referee;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }*/
}
