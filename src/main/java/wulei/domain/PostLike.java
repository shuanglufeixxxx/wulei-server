package wulei.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long postId;
    private Long accountId;
    private String createDate;

    public PostLike() {}

//    public PostLike(PostLikePublic like) {
//        this.id = like.getId();
//        this.postId = like.getPostId();
//        this.accountId = like.getAccountId();
//        this.createDate = like.getCreateDate();
//    }

    public PostLike(Long postId, Long accountId, String createDate) {
        this.id = id;
        this.postId = postId;
        this.accountId = accountId;
        this.createDate = createDate;
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}