package TT.tripTogether.service;

import TT.tripTogether.domain.application.Application;
import TT.tripTogether.domain.post.Post;
import TT.tripTogether.domain.user.User;
import TT.tripTogether.repository.ApplicationRepository;
import TT.tripTogether.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final ApplicationRepository applicationRepository;

    /** 동행자 신청하기 **/
    @Transactional
    public Long create(Long postId, String content) {
        Optional<Post> post = postRepository.findById(postId);
        User user = userService.getUserFromAuth();
        Application application = new Application(user, post.get(), content);
        applicationRepository.save(application);
        user.addApplication(application);
        post.get().addApplication(application);
        return application.getId();
    }
}
