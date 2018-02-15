package wulei.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import wulei.domain.Account;
import wulei.mapper.AccountMapper;


public class AccountService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public Account selectByUsername(String username) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            return session.getMapper(AccountMapper.class).selectByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public int countByUsername(String username) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            return session.getMapper(AccountMapper.class).countByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    public int countByUsernamePassword(String username, String password) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            return session.getMapper(AccountMapper.class).countByUsernamePassword(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    public void insert(String username, String password) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            session.getMapper(AccountMapper.class)
                    .insert( new Account(UUIDUtil.getInstance().generateId(), username, password) );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}