package wulei.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import wulei.domain.FeaturedPicture;
import wulei.domain.Picture;

import java.util.List;

public interface PictureRepository extends CrudRepository<Picture, Long> {

    @Query(value = "select * from picture where id in (select picture_id from featured_picture where place = ?)",
            nativeQuery = true)
    List<Picture> findByPlaceFeatured(String place);

    @Query(value = "select * from picture where id in (select picture_id from picture_collection join picture_item " +
            "on picture_collection.id = picture_item.picture_collection_id where picture_collection_id = ?)",
            nativeQuery = true)
    List<Picture> findByPictureCollectionId(String pictureCollectionId);
}