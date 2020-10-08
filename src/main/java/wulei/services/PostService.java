package wulei.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import wulei.domain.Picture;
import wulei.domain.Post;
import wulei.mapper.PostMapper;
import wulei.repository.PictureRepository;
import wulei.repository.PostRepository;

import java.net.URI;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PictureService pictureService;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PostRepository postRepository;

//    public Post selectById(String id) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            return session.getMapper(PostMapper.class).selectById(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            session.close();
//        }
//    }
//
//    public List<Post> selectByClassify(String classify) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            return session.getMapper(PostMapper.class).selectByClassify(classify);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            session.close();
//        }
//    }
//
//    private void insert(Post post) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            session.getMapper(PostMapper.class).insert(post);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            session.close();
//        }
//    }

    public Long insert(byte[][] pictures,
                       byte[] playbill,
                       String title,
                       String essay,
                       String classify,
                       byte[][] previewPictures,
                       String previewStyle) throws Exception {

        Long pictureCollectionId = pictureService.insertWithPictureCollection( pictures );

        Long playbillId;

        if(playbill != null) {
            playbillId = pictureRepository.save(new Picture(playbill, playbill)).getId();
        }
        else {
            playbillId = null;
        }

        Long previewPictureCollectionId = pictureService.insertWithPictureCollection( previewPictures );

        Post post = postRepository.save( new Post(
            pictureCollectionId,
            playbillId,
            title,
            essay,
            Calendar.getInstance().getTime().toString(),
            classify,
            previewPictureCollectionId,
            previewStyle
        ));

        return post.getId();
    }
}