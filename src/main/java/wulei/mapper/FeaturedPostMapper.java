package wulei.mapper;

import wulei.domain.FeaturedPost;
import wulei.domain.Post;

import java.util.List;

public interface FeaturedPostMapper {
    List<Post> selectByClassify(String classify);

    void insert(FeaturedPost featuredPost);
}
