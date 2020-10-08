package wulei.modelpublic;

import wulei.domain.Picture;

public class PicturePublic {

    private Long id;

    public PicturePublic() {}

    public PicturePublic(Picture picture) {
        this.id = picture.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}