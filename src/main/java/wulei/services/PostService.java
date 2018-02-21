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

    public Long insert(String[] pictureFileNames,
                       String playbillFileName,
                       String title,
                       String essay,
                       String classify,
                       String[] previewPictureFileNames,
                       String previewStyle,
                       String label) throws Exception {

        Long pictureCollectionId = pictureService.insertWithPictureCollection( pictureFileNames );

        Long playbillId;

        if(playbillFileName != null) {
            //playbillId = UUIDUtil.getInstance().generateId();
            byte[] bytes = playbillFileName.getBytes("UTF-8");
            Picture playbill = pictureRepository.save(new Picture(bytes, bytes));
            playbillId = playbill.getId();
        }
        else {
            playbillId = null;
        }

        Long previewPictureCollectionId = pictureService.insertWithPictureCollection( previewPictureFileNames );

        byte[] essayFileNameBytes = null;

        if(essay != null) {
            byte[] essayBytes = essay.getBytes("UTF-8");
            String essayFileName = "essay/essay" + label;
//            FileUtils.writeByteArrayToFile(FileUtils.getFile(essayFileName), essayBytes);
            essayFileNameBytes = essayFileName.getBytes("UTF-8");
        }

        byte[] titleFileNameBytes = null;
        if(title != null) {
            byte[] titleBytes = title.getBytes("UTF-8");
            String titleFileName = "essayTitle/essayTitle" + label;
//            FileUtils.writeByteArrayToFile(FileUtils.getFile(titleFileName), titleBytes);
            titleFileNameBytes = titleFileName.getBytes("UTF-8");
        }

        //String postId = UUIDUtil.getInstance().generateId();
        Post post = postRepository.save( new Post(pictureCollectionId, playbillId, titleFileNameBytes, essayFileNameBytes,
                Calendar.getInstance().getTime().toString(), classify, previewPictureCollectionId, previewStyle) );

        return post.getId();
    }
}