package wulei.domain;

import javax.persistence.*;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] preview;

    @Lob
    private byte[] bytes;

    public Picture() {}

    public Picture(byte[] preview, byte[] bytes) {
        this.preview = preview;
        this.bytes = bytes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPreview() {
        return this.preview;
    }

    public void setPreview(byte[] preview) {
        this.preview = preview;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}