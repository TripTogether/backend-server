package TT.tripTogether.service;

import TT.tripTogether.domain.post.Post;
import TT.tripTogether.domain.post.dto.GetPostsRes;
import TT.tripTogether.domain.post.dto.PostCreateReq;
import TT.tripTogether.domain.user.User;
import TT.tripTogether.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public List<GetPostsRes> getAll() {
        List<Post> posts = postRepository.findAll();
        List<GetPostsRes> getPostsRes =  posts.stream()
                .map(GetPostsRes::new)
                .collect(Collectors.toList());
        return getPostsRes;
    }

    @Transactional
    public Long create(PostCreateReq postCreateReq) {

        User user = userService.getUserFromAuth();

        Post post = new Post(postCreateReq, user);
        return postRepository.save(post).getId();
    }

    public Long test(PostCreateReq postCreateReq, Long userId) {
        User user = userService.findById(userId);

        Post post = new Post(postCreateReq, user);
        return postRepository.save(post).getId();
    }

    public GetPostsRes getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        GetPostsRes getPostsRes = new GetPostsRes(post.get());
        return getPostsRes;
    }

    public Long delete(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        postRepository.delete(post.get());
        return postId;
    }
}
