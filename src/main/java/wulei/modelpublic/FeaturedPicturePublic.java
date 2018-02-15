package wulei.modelpublic;

import wulei.domain.FeaturedPicture;

public class FeaturedPicturePublic {

    private String id;
    private String pictureId;
    private String place;

    public FeaturedPicturePublic() {}

    public FeaturedPicturePublic(FeaturedPicture featuredPicture) {
        this.id = featuredPicture.getId();
        this.pictureId = featuredPicture.getPictureId();
    }

    public FeaturedPicturePublic(String id, String pictureId) {
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