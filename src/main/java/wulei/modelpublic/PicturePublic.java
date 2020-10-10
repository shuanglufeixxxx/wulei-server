package wulei.modelpublic;

import java.math.BigInteger;

import wulei.domain.Picture;

public class PicturePublic {

    private Long id;

    public PicturePublic() {}

    public PicturePublic(Picture picture) {
        this.id = picture.getId();
    }

    public PicturePublic(Long id) {
        this.id = id;
    }

    public PicturePublic(BigInteger id) {
        this.id = id.longValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}