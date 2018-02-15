package wulei.modelpublic;

import wulei.domain.Account;

public class AccountPublic {

    private String id;
    private String username;

    public AccountPublic(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public AccountPublic(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}