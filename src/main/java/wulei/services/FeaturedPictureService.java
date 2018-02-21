//package wulei.services;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import wulei.domain.Picture;
//import wulei.mapper.FeaturedPictureMapper;
//
//import java.util.List;
//
//public class FeaturedPictureService {
//
//    @Autowired
//    SqlSessionFactory sqlSessionFactory;
//
//
//    public List<Picture> selectByPlace(String place) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            return session.getMapper(FeaturedPictureMapper.class).selectByPlace(place);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        finally {
//            session.close();
//        }
//    }
//}
