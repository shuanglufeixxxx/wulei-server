package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import wulei.modelpublic.PicturePublic;
import wulei.repository.PictureRepository;

import java.util.List;

@RestController
@RequestMapping("/picture")
class PictureController {

    @Autowired
    private PictureRepository pictureRepository;

    @GetMapping(params = {"pictureCollectionId"})
    public List<PicturePublic> getList(@RequestParam String pictureCollectionId) {
        return pictureRepository.findByPictureCollectionId(pictureCollectionId);
    }
}
