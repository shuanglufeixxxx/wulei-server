package wulei.modelpublic;

import wulei.domain.Picture;

public class PicturePublic {

    private String id;
    private String previewSource;
    private String source;

    public PicturePublic(String id, String previewSource, String source) {
        this.id = id;
        this.previewSource = previewSource;
        this.source = source;
    }

    public PicturePublic(Picture picture) {
        this.id = picture.getId();
        this.previewSource = picture.getPreviewSource();
        this.source = picture.getSource();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreviewSource() {
        return this.previewSource;
    }

    public void setPreviewSource(String previewSource) {
        this.previewSource = previewSource;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}