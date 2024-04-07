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


    @JoinColumn(name = "player1_id")
    private Long player1Id;


    @JoinColumn(name = "player2_id")
    private Long player2Id;

    @JoinColumn(name = "referee_id")
    private Long refereeId;

    @Column(name = "match_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date matchDate;

    @Column(name = "match_score")
    private String matchScore;

    // Constructors, getters, setters

public Match() {
    }

    public Match(Long id, Long tournamentId, Long player1Id, Long player2Id, Long refereeId, Date matchDate, String matchScore) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.refereeId = refereeId;
        this.matchDate = matchDate;
        this.matchScore = matchScore;
    }

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

    public Long getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(Long player1Id) {
        this.player1Id = player1Id;
    }

    public Long getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(Long player2Id) {
        this.player2Id = player2Id;
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
