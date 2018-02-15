package wulei.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import wulei.domain.Picture;
import wulei.domain.PictureCollection;
import wulei.domain.PictureItem;
import wulei.mapper.PictureMapper;

import java.util.List;

public class PictureService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public Picture selectById(String id) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            return session.getMapper(PictureMapper.class).selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Picture> selectByPictureCollectionId(String pictureCollectionId) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            return session.getMapper(PictureMapper.class).selectByPictureCollectionId(pictureCollectionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void insert(Picture picture) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            session.getMapper(PictureMapper.class).insert(picture);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void insertItem(PictureItem pictureItem) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            session.getMapper(PictureMapper.class).insertItem(pictureItem);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    private void insertCollection(PictureCollection pictureCollection) {
        SqlSession session = this.sqlSessionFactory.openSession(true);
        try {
            session.getMapper(PictureMapper.class).insertCollection(pictureCollection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public String insertWithPictureCollection(String[] pictureFileNames) {
        String pictureCollectionId = UUIDUtil.getInstance().generateId();
        for(String pfn: pictureFileNames) {
            String pictureId = UUIDUtil.getInstance().generateId();
            insert( new Picture( pictureId, pfn, pfn ) );
            insertItem( new PictureItem( UUIDUtil.getInstance().generateId(), pictureCollectionId, pictureId ));
        }
        insertCollection( new PictureCollection(pictureCollectionId) );
        return pictureCollectionId;
    }
}