package wulei.repository;

import org.springframework.data.repository.CrudRepository;
import wulei.domain.PictureItem;

public interface PictureItemRepository extends CrudRepository<PictureItem, Long> {
}
