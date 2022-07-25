package TT.tripTogether.domain.post;

import TT.tripTogether.domain.BaseEntity;
import TT.tripTogether.domain.comment.Comment;
import TT.tripTogether.domain.companion.Companion;
import TT.tripTogether.domain.route.Route;
import TT.tripTogether.domain.scrap.Scrap;
import TT.tripTogether.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String title;

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

    //여행 루트
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Route> routes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Scrap> scraps = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
