package model;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @Column(name = "match_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "tournament_id")
    private Long tournamentId;

    @JoinColumn(name = "player1_id", referencedColumnName = "user_id", nullable = false)
    private Long player1_id;

    @JoinColumn(name = "player2_id", referencedColumnName = "user_id", nullable = false)
    private Long player2_id;

    @JoinColumn(name = "referee_id")
    private Long refereeId;

    @Column(name = "match_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date matchDate;

    @Column(name = "match_score")
    private String matchScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(Long player1_id) {
        this.player1_id = player1_id;
    }

    public Long getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(Long player2_id) {
        this.player2_id = player2_id;
    }

    public Long getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(Long refereeId) {
        this.refereeId = refereeId;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    public String formattedMatchDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mma");
        return sdf.format(matchDate);
    }
}
