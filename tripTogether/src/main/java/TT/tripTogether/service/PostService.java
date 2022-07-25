package TT.tripTogether.service;

import TT.tripTogether.domain.post.Post;
import TT.tripTogether.domain.post.dto.GetPostsRes;
import TT.tripTogether.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<GetPostsRes> getAll() {
        List<Post> posts = postRepository.findAll();
        List<GetPostsRes> getPostsRes =  posts.stream()
                .map(GetPostsRes::new)
                .collect(Collectors.toList());
        return getPostsRes;
    }
}
