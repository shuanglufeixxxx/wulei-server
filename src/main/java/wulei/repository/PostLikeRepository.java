package wulei.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import wulei.domain.PostLike;

import javax.transaction.Transactional;
import java.util.List;

public interface PostLikeRepository extends CrudRepository<PostLike, Long>,
        QueryByExampleExecutor<PostLike> {
    List<PostLike> findByPostId(Long postId);

    @Transactional
    @Modifying
    @Query(value = "delete from post_like where post_id = ? and account_id = ?", nativeQuery = true)
    void deleteByPostIdAccountId(Long postId, Long accountId);
}
