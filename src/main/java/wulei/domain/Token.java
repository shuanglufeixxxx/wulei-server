package wulei.domain;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import java.util.Date;

public class Token {

    private String username;
    private String series;
    private String tokenValue;
    private String lastUsedDate;

    public Token() {}

    public Token(PersistentRememberMeToken persistentRememberMeToken) {
        this.username = persistentRememberMeToken.getUsername();
        this.series = persistentRememberMeToken.getSeries();
        this.tokenValue = persistentRememberMeToken.getTokenValue();
        this.lastUsedDate = persistentRememberMeToken.getDate().toString();
    }

    public PersistentRememberMeToken toPersistentRememberMeToken() {
        return new PersistentRememberMeToken(
                getUsername(), getSeries(), getTokenValue(), new Date( getLastUsedDate() ) );
    }

    public Token(String username, String series, String tokenValue, String lastUsedDate) {
        this.username = username;
        this.series = series;
        this.tokenValue = tokenValue;
        this.lastUsedDate = lastUsedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getLastUsedDate() {
        return lastUsedDate;
    }

    public void setLastUsedDate(String lastUsedDate) {
        this.lastUsedDate = lastUsedDate;
    }
}
