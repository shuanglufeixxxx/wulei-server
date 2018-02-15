package wulei.mapper;

import wulei.domain.Picture;
import wulei.domain.PictureCollection;
import wulei.domain.PictureItem;

import java.util.List;

public interface PictureMapper {
    Picture selectById(String id);

    List<Picture> selectByPictureCollectionId(String pictureCollectionId);

    void insert(Picture picture);

    void insertItem(PictureItem pictureItem);

    void insertCollection(PictureCollection pictureCollection);
}