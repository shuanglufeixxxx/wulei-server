package wulei.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import wulei.domain.Picture;
import wulei.domain.Post;
import wulei.repository.PictureRepository;
import wulei.repository.PostRepository;

import java.util.Calendar;

@Service
public class PostService {

    @Autowired
    PictureService pictureService;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    PostRepository postRepository;

    public Long insert(byte[][] pictures, byte[] playbill, String title, String essay, String classify,
            byte[][] previewPictures, String previewStyle) throws Exception {

        Long pictureCollectionId = pictureService.insertWithPictureCollection(pictures);

        Long playbillId;

        if (playbill != null) {
            playbillId = pictureRepository.save(new Picture(playbill, playbill)).getId();
        } else {
            playbillId = null;
        }

        Long previewPictureCollectionId = pictureService.insertWithPictureCollection(previewPictures);

        Post post = postRepository.save(new Post(pictureCollectionId, playbillId, title, essay,
                Calendar.getInstance().getTime().toString(), classify, previewPictureCollectionId, previewStyle));

        return post.getId();
    }
}