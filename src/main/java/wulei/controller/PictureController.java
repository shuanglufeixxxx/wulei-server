package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import wulei.modelpublic.PicturePublic;
import wulei.repository.PictureRepository;

import java.util.Arrays;
import java.util.List;

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
        return Arrays.asList(pictureRepository.findByPictureCollectionId(pictureCollectionId)
                .stream()
                .map(PicturePublic::new)
                .toArray(PicturePublic[]::new)
        );
    }
}



