package wulei.modelpublic;

import wulei.domain.Post;

public class PostPublic {

    private Long id;
    private Long pictureCollectionId;
    private Long playbillId;
    private String title;
    private String essay;
    private String createDate;
    private String classify;
    private Long previewPictureCollectionId;
    private String previewStyle;

    public PostPublic(Post post) {
        this.id = post.getId();
        this.pictureCollectionId = post.getPictureCollectionId();
        this.playbillId = post.getPlaybillId();
        this.title = post.getTitle();
        this.essay = post.getEssay();
        this.createDate = post.getCreateDate();
        this.classify = post.getClassify();
        this.previewPictureCollectionId = post.getPreviewPictureCollectionId();
        this.previewStyle = post.getPreviewStyle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPictureCollectionId() {
        return this.pictureCollectionId;
    }

    public void setPictureCollectionId(Long pictureCollectionId) {
        this.pictureCollectionId = pictureCollectionId;
    }

    public Long getPlaybillId() {
        return this.playbillId;
    }

    public void setPlaybillId(Long playbillId) {
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

    public Long getPreviewPictureCollectionId() {
        return this.previewPictureCollectionId;
    }

    public void setPreviewPictureCollectionId(Long previewPictureCollectionId) {
        this.previewPictureCollectionId = previewPictureCollectionId;
    }

    public String getPreviewStyle() {
        return this.previewStyle;
    }

    public void setPreviewStyle(String previewStyle) {
        this.previewStyle = previewStyle;
    }
}