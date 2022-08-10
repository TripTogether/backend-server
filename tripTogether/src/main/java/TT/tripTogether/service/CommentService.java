package TT.tripTogether.service;

import TT.tripTogether.domain.comment.Comment;
import TT.tripTogether.domain.comment.dto.GetAllCommentsRes;
import TT.tripTogether.domain.comment.dto.PostCommentReq;
import TT.tripTogether.domain.post.Post;
import TT.tripTogether.domain.user.User;
import TT.tripTogether.repository.CommentRepository;
import TT.tripTogether.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    /** 댓글 달기 **/
    @Transactional
    public Long save(Long postId, PostCommentReq postCommentReq) {
        User user = userService.getUserFromAuth();
        Optional<Post> post = postRepository.findById(postId);
        Comment comment = new Comment(user, post.get(), postCommentReq);
        post.get().addComment(comment);
        return commentRepository.save(comment).getId();
    }

    /** 게시물 내 모든 댓글 보기 **/
    public List<GetAllCommentsRes> getAllComments(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        List<Comment> comments = post.get().getComments();
        List<GetAllCommentsRes> getAllCommentsRes = comments.stream()
                .map(GetAllCommentsRes::new)
                .collect(Collectors.toList());
        return getAllCommentsRes;
    }
}
