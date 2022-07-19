package TT.tripTogether.domain.post;

import TT.tripTogether.domain.Companion;
import TT.tripTogether.domain.route.Route;
import TT.tripTogether.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    //구하는 인원 수
    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //여행 지역
    @Enumerated(EnumType.STRING)
    private Region region;

    private LocalDate startDate;

    private LocalDate endDate;

    //동행자 리스트 추가
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Companion> companions = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Route> routes = new ArrayList<>();

    //사진 리스트
//    private List<> picture = new ArrayList<>();

}
