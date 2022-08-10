package TT.tripTogether.controller;

import TT.tripTogether.config.exception.BaseResponse;
import TT.tripTogether.domain.comment.dto.GetAllCommentsRes;
import TT.tripTogether.domain.comment.dto.PostCommentReq;
import TT.tripTogether.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("post/{postId}")
public class CommentController {
    private final CommentService commentService;

    @ApiOperation("댓글 달기")
    @PostMapping("/new")
    private BaseResponse<Long> createComment(@PathVariable Long postId, @RequestBody PostCommentReq postCommentReq) {
        return new BaseResponse<Long>(commentService.save(postId, postCommentReq));
    }

    @ApiOperation("게시물 내 모든 댓글 보기")
    @GetMapping("/comments")
    private BaseResponse<List<GetAllCommentsRes>> getAll(@PathVariable Long postId) {
        return new BaseResponse<>(commentService.getAllComments(postId));
    }
}
