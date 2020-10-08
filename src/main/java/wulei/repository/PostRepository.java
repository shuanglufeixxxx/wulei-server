package wulei.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import wulei.domain.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query(value = "select * from post where id in (select post_id from featured_post where classify = ?)",
            nativeQuery = true)
    List<Post> findByClassifyFeatured(String classify);

    @Query(value = "select * from post where id in (select post_id from post_like where account_id = ?)",
            nativeQuery = true)
    List<Post> findByAccountId(Long accountId);
}
