package wulei.domain;

import wulei.modelpublic.FeaturedPostPublic;

public class FeaturedPost {

    private String id;
    private String postId;

    public FeaturedPost() {}

    public FeaturedPost(FeaturedPostPublic activityFeaturedPost) {
        this.id = activityFeaturedPost.getId();
        this.postId = activityFeaturedPost.getPostId();
    }

    public FeaturedPost(String id, String postId) {
        this.id = id;
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return this.postId;
    }


    public void setPostId(String postId) {
        this.postId = postId;
    }
}