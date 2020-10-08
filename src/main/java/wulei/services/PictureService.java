package wulei.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import wulei.domain.Picture;
import wulei.domain.PictureCollection;
import wulei.domain.PictureItem;
import wulei.repository.PictureCollectionRepository;
import wulei.repository.PictureItemRepository;
import wulei.repository.PictureRepository;

@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PictureCollectionRepository pictureCollectionRepository;

    @Autowired
    PictureItemRepository pictureItemRepository;

    public Long insertWithPictureCollection(byte[][] pictures) throws Exception {
        PictureCollection pictureCollection = pictureCollectionRepository.save( new PictureCollection() );
        for(byte[] p: pictures) {
            Picture picture = pictureRepository.save( new Picture( p, p ) );
            pictureItemRepository.save( new PictureItem( pictureCollection.getId(), picture.getId() ));
        }
        return pictureCollection.getId();
    }
}