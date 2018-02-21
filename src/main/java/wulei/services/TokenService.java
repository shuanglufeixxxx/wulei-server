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

//    public void createNewToken(PersistentRememberMeToken token) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            session.getMapper(TokenMapper.class).createNewToken(new Token(token));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    public void updateToken(String series, String tokenValue, Date lastUsed) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            session.getMapper(TokenMapper.class).updateToken( series, tokenValue, lastUsed.toString() );
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
//        if(seriesId == null) {
//            return null;
//        }
//
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            return session.getMapper(TokenMapper.class).getTokenForSeries(seriesId).toPersistentRememberMeToken();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            session.close();
//        }
//    }
//
//    public void removeUserTokens(String username) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            session.getMapper(TokenMapper.class).removeUserTokens(username);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }


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
