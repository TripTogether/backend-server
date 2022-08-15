package TT.tripTogether.controller;

import TT.tripTogether.config.exception.BaseResponse;
import TT.tripTogether.domain.post.dto.GetDetailRes;
import TT.tripTogether.domain.post.dto.GetPostsRes;
import TT.tripTogether.domain.post.dto.PostCreateReq;
import TT.tripTogether.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    @ApiOperation("모든 게시물 보기")
    public BaseResponse<List<GetPostsRes>> getPosts() {
        return new BaseResponse<List<GetPostsRes>>(postService.getAll());
    }

    @ApiOperation("게시물 상세 보기")
    @GetMapping("/{postId}")
    public BaseResponse<GetDetailRes> getPost(@PathVariable Long postId) {
        return new BaseResponse<>(postService.getPost(postId));
    }

    @PostMapping("/new")
    @ApiOperation("게시물 작성")
    public BaseResponse<Long> getPost(@RequestBody PostCreateReq postCreateReq) {
        return new BaseResponse<>(postService.create(postCreateReq));
    }
    @PostMapping("/test/{userId}")
    @ApiOperation("Post-Create feature test")
    public BaseResponse<Long> test(@RequestBody PostCreateReq postCreateReq, @PathVariable Long userId) {
        return new BaseResponse<Long>(postService.test(postCreateReq, userId));
    }

    @DeleteMapping("/test/{postId}")
    @ApiOperation("게시물 삭제")
    public BaseResponse<Long> deletePost(@PathVariable Long postId) {
        return new BaseResponse<Long>(postService.delete(postId));
    }

}
