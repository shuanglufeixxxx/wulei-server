package wulei.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import wulei.domain.FeaturedPost;
import wulei.domain.Post;
import wulei.mapper.FeaturedPostMapper;

import java.util.List;

public class FeaturedPostService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;


    public List<Post> selectByClassify(String classify) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            return session.getMapper(FeaturedPostMapper.class).selectByClassify(classify);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
    }
    
    public void insert(FeaturedPost featuredPost) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            session.getMapper(FeaturedPostMapper.class).insert(featuredPost);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
