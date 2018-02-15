package wulei.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import wulei.modelpublic.PicturePublic;
import wulei.service.PictureService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/picture")
class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/{id}")
    public PicturePublic get(@PathVariable String id) {
        return new PicturePublic( this.pictureService.selectById(id) );
    }

    @GetMapping
    public List<PicturePublic> getList(@RequestParam String pictureCollectionId) {
        return Arrays.asList(this.pictureService
                .selectByPictureCollectionId( pictureCollectionId )
                .stream()
                .map(PicturePublic::new)
                .toArray(PicturePublic[]::new)
        );
    }
}