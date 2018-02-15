package wulei.mapper;

import wulei.domain.Token;

public interface TokenMapper {

    void createNewToken(Token token);

    void updateToken(String series, String tokenValue, String lastUsed);

    Token getTokenForSeries(String seriesId);

    void removeUserTokens(String username);
}
