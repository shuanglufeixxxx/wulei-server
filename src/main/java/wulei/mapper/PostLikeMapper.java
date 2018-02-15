package wulei.mapper;

import wulei.domain.Post;
import wulei.domain.PostLike;

import java.util.List;

public interface PostLikeMapper {
    int countByPostId(String id);

    int countByPostIdUserId(String postId, String accountId);

    List<Post> selectPostLiked(String accountId);

    void insert(PostLike postLike);

    void remove(String postId, String accountId);
}