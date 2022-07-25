package TT.tripTogether.domain.post.dto;

import TT.tripTogether.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetPostsRes {
    private Long id;
    private String title;
    private String content;
    private int number;
    private int currentNum;
    private int scrapNum;
    private int commentNum;
    private LocalDate startDate;
    private LocalDate endDate;

    public GetPostsRes(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.number = post.getNumber();
        this.currentNum = post.getCompanions().size() + 1;
        this.scrapNum = post.getScraps().size();
        this.commentNum = post.getComments().size();
        this.startDate = post.getStartDate();
        this.endDate = post.getEndDate();
    }

}
