package TT.tripTogether.domain.post;

import TT.tripTogether.domain.BaseEntity;
import TT.tripTogether.domain.application.Application;
import TT.tripTogether.domain.comment.Comment;
import TT.tripTogether.domain.companion.Companion;
import TT.tripTogether.domain.post.dto.PostCreateReq;
import TT.tripTogether.domain.route.Route;
import TT.tripTogether.domain.route.dto.RouteDto;
import TT.tripTogether.domain.scrap.Scrap;
import TT.tripTogether.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();

    public Post(PostCreateReq postCreateReq, User user) {
        this.user = user;
        this.title = postCreateReq.getTitle();
        this.content = postCreateReq.getContent();
        this.number = postCreateReq.getNumber();
        this.region = postCreateReq.getRegion();
        this.startDate = postCreateReq.getStartDate();
        this.endDate = postCreateReq.getEndDate();
        /** 이 부분 수정하기 !!! 어떻게 가져올지
         * Post -> Route -> Attraction
         * **/
        this.routes = postCreateReq.getRoutes()
                .stream()
                .map(Route::new)
                .collect(Collectors.toList());

    }

    /** 댓글 추가 **/
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
