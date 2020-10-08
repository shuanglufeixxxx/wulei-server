package wulei.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import wulei.domain.Token;
import wulei.repository.TokenRepository;

import java.util.Date;

@Service
public class TokenService implements PersistentTokenRepository {

    @Autowired
    TokenRepository tokenRepository;


    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        tokenRepository.save(new Token(token));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        tokenRepository.save( new Token( series, null, tokenValue, lastUsed.toString() ) );
    }

    @Override
    public void removeUserTokens(String username) {
        tokenRepository.deleteByUsername(username);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        if(seriesId == null) { return null; }

        Token token = tokenRepository.findOne(seriesId);
        return token == null ? null : token.toPersistentRememberMeToken();
    }
}
