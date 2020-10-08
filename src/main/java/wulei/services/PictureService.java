package wulei.services;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import wulei.domain.Picture;
import wulei.domain.PictureCollection;
import wulei.domain.PictureItem;
import wulei.mapper.PictureMapper;
import wulei.repository.PictureCollectionRepository;
import wulei.repository.PictureItemRepository;
import wulei.repository.PictureRepository;

import java.net.URI;
import java.nio.file.Paths;

@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PictureCollectionRepository pictureCollectionRepository;

    @Autowired
    PictureItemRepository pictureItemRepository;

//    public Picture selectById(String id) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            return session.getMapper(PictureMapper.class).selectById(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            session.close();
//        }
//    }
//
//    public List<Picture> selectByPictureCollectionId(String pictureCollectionId) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            return session.getMapper(PictureMapper.class).selectByPictureCollectionId(pictureCollectionId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            session.close();
//        }
//    }
//
//    public void insert(Picture picture) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            session.getMapper(PictureMapper.class).insert(picture);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    private void insertItem(PictureItem pictureItem) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            session.getMapper(PictureMapper.class).insertItem(pictureItem);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            session.close();
//        }
//    }
//
//    private void insertCollection(PictureCollection pictureCollection) {
//        SqlSession session = this.sqlSessionFactory.openSession(true);
//        try {
//            session.getMapper(PictureMapper.class).insertCollection(pictureCollection);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            session.close();
//        }
//    }

    public Long insertWithPictureCollection(byte[][] pictures) throws Exception {
        PictureCollection pictureCollection = pictureCollectionRepository.save( new PictureCollection() );
        for(byte[] p: pictures) {
            Picture picture = pictureRepository.save( new Picture( p, p ) );
            pictureItemRepository.save( new PictureItem( pictureCollection.getId(), picture.getId() ));
        }
        return pictureCollection.getId();
    }
}