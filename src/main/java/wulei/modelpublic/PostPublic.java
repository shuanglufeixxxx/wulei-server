package wulei.modelpublic;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import wulei.domain.Post;

import java.io.IOException;

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
        this.playbillId = post.getPlaybill();
        try {
            byte[] titleTemp = post.getTitle();
            byte[] essayTemp = post.getEssay();
            if(titleTemp != null) {
                String titleFileName = IOUtils.toString(titleTemp, "UTF-8");
                this.title = FileUtils.readFileToString(FileUtils.getFile(titleFileName), "UTF-8");
            }

            if(essayTemp != null) {
                String essayFileName = IOUtils.toString(essayTemp, "UTF-8");
                this.essay = FileUtils.readFileToString(FileUtils.getFile(essayFileName), "UTF-8");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.createDate = post.getCreateDate();
        this.classify = post.getClassify();
        this.previewPictureCollectionId = post.getPreviewPictureCollectionId();
        this.previewStyle = post.getPreviewStyle();
    }

//    public PostPublic(String id, String pictureCollectionId, String playbillId, String title, String essay,
//                      String createDate, String classify, String previewPictureCollectionId, String previewStyle) {
//        this.id = id;
//        this.pictureCollectionId = pictureCollectionId;
//        this.playbillId = playbillId;
//        this.title = title;
//        this.essay = essay;
//        this.createDate = createDate;
//        this.classify = classify;
//        this.previewPictureCollectionId = previewPictureCollectionId;
//        this.previewStyle = previewStyle;
//    }

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

    public Long getPlaybill() {
        return this.playbillId;
    }

    public void setPlaybill(Long playbillId) {
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