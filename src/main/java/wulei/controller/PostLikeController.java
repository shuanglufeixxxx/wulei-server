package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wulei.domain.PostLike;
import wulei.modelpublic.PostPublic;
import wulei.modelpublic.Value;
import wulei.repository.PostLikeRepository;
import wulei.repository.PostRepository;
import wulei.security.AccountDetails;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/postLike")
class PostLikeController {

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/count")
    public Value<Integer> count(@RequestParam Long postId) {
        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        return new Value<Integer>( (int) (long) this.postLikeRepository.count( Example.of(postLike) ) );
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/exist")
    public Value<Boolean> exist(@RequestParam Long postId,
                                @AuthenticationPrincipal AccountDetails accountDetails) {
        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setAccountId(accountDetails.getId());

        return new Value<Boolean>( this.postLikeRepository.exists( Example.of(postLike) ) );
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/my")
    public List<PostPublic> getPostLiked(@AuthenticationPrincipal AccountDetails accountDetails) {
        return Arrays.asList(postRepository.findByAccountId(accountDetails.getId())
                .stream()
                .map(PostPublic::new)
                .toArray(PostPublic[]::new)
        );
    }

    @Secured("ROLE_USER")
    @PostMapping
    public Value<String> insert(@RequestBody PostLike postLike,
                                @AuthenticationPrincipal AccountDetails accountDetails) {

        postLike.setAccountId( accountDetails.getId() );
        postLike.setCreateDate( Calendar.getInstance().getTime().toString() );

        this.postLikeRepository.save(postLike);
        return new Value<>(null);
    }

    @Secured("ROLE_USER")
    @DeleteMapping
    public Value<String> remove(@RequestParam Long postId,
                                @AuthenticationPrincipal AccountDetails accountDetails) {

        this.postLikeRepository.deleteByPostIdAccountId(postId, accountDetails.getId());

        return new Value<>(null);
    }
}