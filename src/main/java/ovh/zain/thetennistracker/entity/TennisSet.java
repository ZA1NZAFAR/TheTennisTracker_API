package ovh.zain.thetennistracker.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tennis_Set")
@Getter
@Setter
public class TennisSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "match_id")
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Long matchId;

    @Column(name = "set_number")
    private Integer setNumber;

    @Column(name = "player_1_score")
    private Integer player1Score;

    @Column(name = "player_2_score")
    private Integer player2Score;

}
