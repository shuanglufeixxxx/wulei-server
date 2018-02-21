package wulei.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import wulei.domain.FeaturedPost;

import javax.transaction.Transactional;
import java.util.List;

public interface FeaturedPostRepository extends CrudRepository<FeaturedPost, Long> {

//    @Transactional
//    @Modifying
//    @Query(value = "update user set max_questions = 0;", nativeQuery = true)
//    void modify();
}
