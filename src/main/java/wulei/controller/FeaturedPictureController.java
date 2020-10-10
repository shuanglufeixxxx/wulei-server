package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wulei.modelpublic.PicturePublic;
import wulei.repository.PictureRepository;

import java.util.List;

@RestController
@RequestMapping("/featuredPicture")
public class FeaturedPictureController {

    @Autowired
    private PictureRepository pictureRepository;

    @GetMapping
    public List<PicturePublic> get(@RequestParam String place) {
        return pictureRepository.findByPlaceFeatured(place);
    }
}
