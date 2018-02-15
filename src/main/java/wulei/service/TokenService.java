package wulei.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import wulei.domain.Token;
import wulei.mapper.TokenMapper;

import java.util.Date;

public class TokenService implements PersistentTokenRepository {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public void createNewToken(PersistentRememberMeToken token) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            session.getMapper(TokenMapper.class).createNewToken(new Token(token));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateToken(String series, String tokenValue, Date lastUsed) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            session.getMapper(TokenMapper.class).updateToken( series, tokenValue, lastUsed.toString() );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        if(seriesId == null) {
            return null;
        }

        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            return session.getMapper(TokenMapper.class).getTokenForSeries(seriesId).toPersistentRememberMeToken();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void removeUserTokens(String username) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            session.getMapper(TokenMapper.class).removeUserTokens(username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
