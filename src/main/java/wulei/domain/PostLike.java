package wulei.domain;

import wulei.modelpublic.PostLikePublic;

public class PostLike {

    private String id;
    private String postId;
    private String accountId;
    private String createDate;

    public PostLike() {}

    public PostLike(PostLikePublic like) {
        this.id = like.getId();
        this.postId = like.getPostId();
        this.accountId = like.getAccountId();
        this.createDate = like.getCreateDate();
    }

    public PostLike(String id, String postId, String accountId, String createDate) {
        this.id = id;
        this.postId = postId;
        this.accountId = accountId;
        this.createDate = createDate;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}