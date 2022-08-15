package TT.tripTogether.domain.route;

import TT.tripTogether.domain.attraction.Attraction;
import TT.tripTogether.domain.post.Post;
import TT.tripTogether.domain.route.dto.RouteDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long id;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Attraction> attractions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Route(RouteDto routeDto) {
        this.attractions = routeDto.getAttractions()
                .stream()
                .map(Attraction::new)
                .collect(Collectors.toList());
    }
}
