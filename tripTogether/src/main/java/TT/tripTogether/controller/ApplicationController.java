package TT.tripTogether.controller;

import TT.tripTogether.config.exception.BaseResponse;
import TT.tripTogether.service.ApplicationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("post/{postId}")
public class ApplicationController {

    private final ApplicationService applicationService;

    @ApiOperation("동행자 신청")
    @PostMapping("/application")
    public BaseResponse<Long> apply(@PathVariable Long postId, String content) {
        return new BaseResponse<Long>(applicationService.create(postId, content));
    }
}
