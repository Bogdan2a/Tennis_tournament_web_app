package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "referee_schedule")
public class RefereeSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "referee_id")
    private User referee;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    // Constructors, getters, setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReferee() {
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
    }
}
