package wulei.domain;

import javax.persistence.*;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private byte[] previewSource;

    @Lob
    private byte[] source;

    public Picture() {}

//    public Picture(PicturePublic picture) {
//        this.id = picture.getId();
//        this.previewSource = picture.getPreviewSource();
//        this.source = picture.getSource();
//    }

    public Picture(byte[] previewSource, byte[] source) {
        this.previewSource = previewSource;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPreviewSource() {
        return this.previewSource;
    }

    public void setPreviewSource(byte[] previewSource) {
        this.previewSource = previewSource;
    }

    public byte[] getSource() {
        return this.source;
    }

    public void setSource(byte[] source) {
        this.source = source;
    }
}