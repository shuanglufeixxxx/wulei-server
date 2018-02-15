package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wulei.modelpublic.PicturePublic;
import wulei.service.FeaturedPictureService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("featuredPicture")
public class FeaturedPictureController {

    @Autowired
    private FeaturedPictureService featuredPictureService;

    @GetMapping
    public List<PicturePublic> get(@RequestParam String place) {
        return Arrays.asList(featuredPictureService
                .selectByPlace(place)
                .stream()
                .map(PicturePublic::new)
                .toArray(PicturePublic[]::new)
        );
    }
}
