package wulei.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import wulei.domain.Picture;
import wulei.modelpublic.PicturePublic;

import java.util.List;

public interface PictureRepository extends CrudRepository<Picture, Long> {

    @Query(value = "select id from picture where id in (select picture_id from featured_picture where place = ?)",
            nativeQuery = true)
    List<PicturePublic> findByPlaceFeatured(String place);

    @Query(value = "select id from picture where id in (select picture_id from picture_collection join picture_item " +
            "on picture_collection.id = picture_item.picture_collection_id where picture_collection_id = ?)",
            nativeQuery = true)
    List<PicturePublic> findByPictureCollectionId(String pictureCollectionId);
}