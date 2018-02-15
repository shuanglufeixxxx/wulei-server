package wulei.mapper;

import wulei.domain.Post;

import java.util.List;

public interface PostMapper {
    Post selectById(String id);

    List<Post> selectByClassify(String classify);

    void insert(Post post);
}