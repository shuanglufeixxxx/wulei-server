package wulei.repository;

import org.springframework.data.repository.CrudRepository;
import wulei.domain.PictureCollection;

public interface PictureCollectionRepository extends CrudRepository<PictureCollection, Long> {
}
