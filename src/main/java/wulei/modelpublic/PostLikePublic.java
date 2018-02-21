//package wulei.modelpublic;
//
//import wulei.domain.PostLike;
//
//public class PostLikePublic {
//
//    private String id;
//    private String postId;
//    private String accountId;
//    private String createDate;
//
//    public PostLikePublic() {}
//
//    public PostLikePublic(String id, String postId, String accountId, String createDate) {
//        this.id = id;
//        this.postId = postId;
//        this.accountId = accountId;
//        this.createDate = createDate;
//    }
//
//    public PostLikePublic(PostLike postLike) {
//        this.id = postLike.getId();
//        this.postId = postLike.getPostId();
//        this.accountId = postLike.getAccountId();
//        this.createDate = postLike.getCreateDate();
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getPostId() {
//        return this.postId;
//    }
//
//    public void setPostId(String postId) {
//        this.postId = postId;
//    }
//
//    public String getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountId(String accountId) {
//        this.accountId = accountId;
//    }
//
//    public String getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(String createDate) {
//        this.createDate = createDate;
//    }
//}