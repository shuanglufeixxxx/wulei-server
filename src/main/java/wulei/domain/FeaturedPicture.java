package wulei.domain;

import wulei.modelpublic.FeaturedPicturePublic;

public class FeaturedPicture {

    private String id;
    private String pictureId;
    private String place;

    public FeaturedPicture() {}

    public FeaturedPicture(FeaturedPicturePublic featuredPicturePublic) {
        this.id = featuredPicturePublic.getId();
        this.pictureId = featuredPicturePublic.getPictureId();
    }

    public FeaturedPicture(String id, String pictureId) {
        this.id = id;
        this.pictureId = pictureId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPictureId() {
        return this.pictureId;
    }


    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }
}