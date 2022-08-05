package TT.tripTogether.controller;

import TT.tripTogether.config.exception.BaseResponse;
import TT.tripTogether.domain.comment.dto.PostCommentReq;
import TT.tripTogether.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("post/{postId}")
public class CommentController {
    private final CommentService commentService;

    @ApiOperation("댓글 달기")
    @PostMapping("")
    private BaseResponse<Long> createComment(@PathVariable Long postId, @RequestBody PostCommentReq postCommentReq) {
        return new BaseResponse<Long>(commentService.save(postId, postCommentReq));
    }
}
