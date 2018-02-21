package wulei.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FeaturedPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long postId;

    public FeaturedPost() {}

//    public FeaturedPost(FeaturedPostPublic activityFeaturedPost) {
//        this.id = activityFeaturedPost.getId();
//        this.postId = activityFeaturedPost.getPostId();
//    }

    public FeaturedPost(Long postId) {
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return this.postId;
    }


    public void setPostId(Long postId) {
        this.postId = postId;
    }
}