package wulei.modelpublic;

import wulei.domain.Post;

public class PostPublic {

    private String id;
    private String pictureCollectionId;
    private String playbillId;
    private String title;
    private String essay;
    private String createDate;
    private String classify;
    private String previewPictureCollectionId;
    private String previewStyle;

    public PostPublic(Post post) {
        this.id = post.getId();
        this.pictureCollectionId = post.getPictureCollectionId();
        this.playbillId = post.getPlaybill();
        this.title = post.getTitle();
        this.essay = post.getEssay();
        this.createDate = post.getCreateDate();
        this.classify = post.getClassify();
        this.previewPictureCollectionId = post.getPreviewPictureCollectionId();
        this.previewStyle = post.getPreviewStyle();
    }

    public PostPublic(String id, String pictureCollectionId, String playbillId, String title, String essay,
                      String createDate, String classify, String previewPictureCollectionId, String previewStyle) {
        this.id = id;
        this.pictureCollectionId = pictureCollectionId;
        this.playbillId = playbillId;
        this.title = title;
        this.essay = essay;
        this.createDate = createDate;
        this.classify = classify;
        this.previewPictureCollectionId = previewPictureCollectionId;
        this.previewStyle = previewStyle;
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

    public String getPlaybill() {
        return this.playbillId;
    }

    public void setPlaybill(String playbillId) {
        this.playbillId = playbillId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEssay() {
        return this.essay;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getClassify() {
        return this.classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getPreviewPictureCollectionId() {
        return this.previewPictureCollectionId;
    }

    public void setPreviewPictureCollectionId(String previewPictureCollectionId) {
        this.previewPictureCollectionId = previewPictureCollectionId;
    }

    public String getPreviewStyle() {
        return this.previewStyle;
    }

    public void setPreviewStyle(String previewStyle) {
        this.previewStyle = previewStyle;
    }
}