package TT.tripTogether.domain.comment.dto;

import TT.tripTogether.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GetAllCommentsRes {
    private Long id;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;

    public GetAllCommentsRes(Comment comment) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
