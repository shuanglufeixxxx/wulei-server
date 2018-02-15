package wulei.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import wulei.domain.Picture;
import wulei.domain.Post;
import wulei.mapper.PostMapper;

import java.util.Calendar;
import java.util.List;

public class PostService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    PictureService pictureService;

    public Post selectById(String id) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            return session.getMapper(PostMapper.class).selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Post> selectByClassify(String classify) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            return session.getMapper(PostMapper.class).selectByClassify(classify);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    private void insert(Post post) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            session.getMapper(PostMapper.class).insert(post);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public String insert(String[] pictureFileNames,
                       String playbillFileName,
                       String title,
                       String essay,
                       String classify,
                       String[] previewPictureFileNames,
                       String previewStyle) {

        String pictureCollectionId = pictureService.insertWithPictureCollection( pictureFileNames );

        String playbillId;

        if(playbillFileName != null) {
            playbillId = UUIDUtil.getInstance().generateId();
            pictureService.insert(new Picture(playbillId, playbillFileName, playbillFileName));
        }
        else {
            playbillId = null;
        }

        String previewPictureCollectionId = pictureService.insertWithPictureCollection( previewPictureFileNames );

        String postId = UUIDUtil.getInstance().generateId();
        insert( new Post(postId, pictureCollectionId, playbillId, title, essay,
                Calendar.getInstance().getTime().toString(), classify, previewPictureCollectionId, previewStyle) );

        return postId;
    }
}