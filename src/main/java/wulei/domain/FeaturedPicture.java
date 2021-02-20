package wulei.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FeaturedPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pictureId;
    private String place;

    public FeaturedPicture() {}

    public FeaturedPicture(Long pictureId, String place) {
        this.pictureId = pictureId;
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPictureId() {
        return this.pictureId;
    }


    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }
}