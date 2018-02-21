package wulei.modelpublic;

import org.apache.commons.io.IOUtils;
import wulei.domain.Picture;

public class PicturePublic {

    private Long id;
    private String previewSource;
    private String source;

//    public PicturePublic(String id, String previewSource, String source) {
//        this.id = id;
//        this.previewSource = previewSource;
//        this.source = source;
//    }

    public PicturePublic() {}

    public PicturePublic(Picture picture) {
        this.id = picture.getId();
        try {
            this.previewSource = IOUtils.toString(picture.getPreviewSource(), "UTF-8");
            this.source = IOUtils.toString(picture.getSource(), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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