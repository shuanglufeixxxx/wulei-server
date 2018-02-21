//package wulei.services;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import wulei.domain.Post;
//import wulei.domain.PostLike;
//import wulei.mapper.PostLikeMapper;
//
//import java.util.List;
//
//public class PostLikeService {
//
//    @Autowired
//    SqlSessionFactory sqlSessionFactory;
//
//
//    public int countByPostId(String id) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            return session.getMapper(PostLikeMapper.class).countByPostId(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        } finally {
//            session.close();
//        }
//    }
//
//    public int countByPostIdUserId(String postId, String accountId) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            return session.getMapper(PostLikeMapper.class).countByPostIdUserId(postId, accountId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        } finally {
//            session.close();
//        }
//    }
//
//
//    public List<Post> selectPostLiked(String accountId) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            return session.getMapper(PostLikeMapper.class).selectPostLiked(accountId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            session.close();
//        }
//    }
//
//    public void insert(PostLike postLike) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            session.getMapper(PostLikeMapper.class).insert(postLike);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    public void remove(String postId, String accountId) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            session.getMapper(PostLikeMapper.class).remove(postId, accountId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//}