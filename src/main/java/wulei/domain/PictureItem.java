package wulei.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PictureItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pictureCollectionId;
    private Long pictureId;

    public PictureItem() {}

    public PictureItem(Long pictureCollectionId, Long pictureId) {
        this.pictureCollectionId = pictureCollectionId;
        this.pictureId = pictureId;
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

    public Long getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }
}