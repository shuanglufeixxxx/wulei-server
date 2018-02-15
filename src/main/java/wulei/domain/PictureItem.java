package wulei.domain;

public class PictureItem {

    private String id;
    private String pictureCollectionId;
    private String pictureId;

    public PictureItem() {}

    public PictureItem(String id, String pictureCollectionId, String pictureId) {
        this.id = id;
        this.pictureCollectionId = pictureCollectionId;
        this.pictureId = pictureId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPictureCollectionId() {
        return this.pictureCollectionId;
    }

    public void setPictureCollectionId(String pictureCollectionId) {
        this.pictureCollectionId = pictureCollectionId;
    }

    public String getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }
}