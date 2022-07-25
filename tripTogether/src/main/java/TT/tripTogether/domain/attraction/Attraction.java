package TT.tripTogether.domain.attraction;

import TT.tripTogether.domain.BaseEntity;
import TT.tripTogether.domain.route.Route;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Attraction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attraction_id")
    private Long id;

    private String name;

    //위도, 경도
    private Point point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;
}
