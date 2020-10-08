package wulei.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import wulei.domain.Picture;
import wulei.modelpublic.PicturePublic;
import wulei.repository.PictureRepository;
import wulei.services.PictureService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/picture")
class PictureController {

    @Autowired
    private PictureRepository pictureRepository;

    @GetMapping("/{id}")
    public PicturePublic get(@PathVariable long id) {
        return new PicturePublic(this.pictureRepository.findOne(id));
    }

    @GetMapping(params = {"pictureCollectionId"})
    public List<PicturePublic> getList(@RequestParam String pictureCollectionId) {
//        return Arrays.asList(this.pictureRepository
//                .findByPictureCollectionId( pictureCollectionId )
//                .stream()
//                .map(Picture::new)
//                .toArray(Picture[]::new)
//        );
        return Arrays.asList(pictureRepository.findByPictureCollectionId(pictureCollectionId)
                .stream()
                .map(PicturePublic::new)
                .toArray(PicturePublic[]::new)
        );
    }

//    @GetMapping
//    public List<PicturePublic> getList2() {
//        List<Picture> pictureList = new ArrayList<Picture>();
//        pictureRepository.findAll().forEach(pictureList::add);
//
//        return Arrays.asList(pictureList
//                .stream()
//                .map(PicturePublic::new)
//                .toArray(PicturePublic[]::new)
//        );
//    }
}



