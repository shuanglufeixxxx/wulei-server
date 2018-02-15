package wulei.mapper;

import wulei.domain.Picture;

import java.util.List;

public interface FeaturedPictureMapper {
    List<Picture> selectByPlace(String place);
}
