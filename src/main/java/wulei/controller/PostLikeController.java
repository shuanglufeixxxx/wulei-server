package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wulei.domain.Post;
import wulei.domain.PostLike;
import wulei.modelpublic.PostLikePublic;
import wulei.modelpublic.Value;
import wulei.security.AccountDetails;
import wulei.service.PostLikeService;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/postLike")
class PostLikeController {

    @Autowired
    private PostLikeService postLikeService;

    @GetMapping(value = "/count")
    public Value<Integer> count(@RequestParam String postId) {
        return new Value<Integer>( this.postLikeService.countByPostId(postId) );
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/exist")
    public Value<Boolean> exist(@RequestParam String postId,
                                @AuthenticationPrincipal AccountDetails accountDetails) {
        return new Value<Boolean>( this.postLikeService.countByPostIdUserId( postId, accountDetails.getId() ) > 0 );
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/my")
    public List<Post> getPostLiked(@AuthenticationPrincipal AccountDetails accountDetails) {
        return postLikeService.selectPostLiked( accountDetails.getId() );
    }

    @Secured("ROLE_USER")
    @PostMapping
    public Value<String> insert(@RequestBody PostLikePublic postLikePublic,
                                @AuthenticationPrincipal AccountDetails accountDetails) {
        postLikePublic.setAccountId( accountDetails.getId() );
        postLikePublic.setCreateDate( Calendar.getInstance().getTime().toString() );
        this.postLikeService.insert( new PostLike(postLikePublic) );
        return new Value<>(null);
    }

    @Secured("ROLE_USER")
    @DeleteMapping
    public Value<String> remove(@RequestParam String postId,
                                @AuthenticationPrincipal AccountDetails accountDetails) {
        this.postLikeService.remove(postId, accountDetails.getId());
        return new Value<>(null);
    }
}