package ovh.zain.thetennistracker.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Tennis")
public class Tennis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "player_1")
    private String player1;

    @Column(name = "player_2")
    private String player2;

    @Column(name = "match_date")
    private Date matchDate;

    @Column(name = "match_location")
    private String matchLocation;

    @Column(name = "longitude", precision = 10, scale = 8)
    private BigDecimal longitude;

    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;

    @OneToMany(mappedBy = "matchId")
    private List<TennisSet> tennisSets;

}
