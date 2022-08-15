package TT.tripTogether.domain.post.dto;

import TT.tripTogether.domain.comment.dto.GetAllCommentsRes;
import TT.tripTogether.domain.post.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class GetDetailRes {
    private Long id;
    private String title;
    private String content;
    private int number;
    private int currentNum;
    private int scrapNum;
    private int commentNum;
    private List<GetAllCommentsRes> comments;
    private LocalDate startDate;
    private LocalDate endDate;

    public GetDetailRes(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.number = post.getNumber();
        this.currentNum = post.getCompanions().size() + 1;
        this.scrapNum = post.getScraps().size();
        this.commentNum = post.getComments().size();
        this.startDate = post.getStartDate();
        this.endDate = post.getEndDate();
        this.comments = post.getComments()
                .stream()
                .map(GetAllCommentsRes::new)
                .collect(Collectors.toList());
    }

}
