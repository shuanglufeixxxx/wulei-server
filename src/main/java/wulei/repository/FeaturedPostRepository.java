package wulei.repository;

import org.springframework.data.repository.CrudRepository;
import wulei.domain.FeaturedPost;

public interface FeaturedPostRepository extends CrudRepository<FeaturedPost, Long> {
}
