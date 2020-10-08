package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wulei.modelpublic.PicturePublic;
import wulei.repository.PictureRepository;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    PictureRepository pictureRepository;

    // @GetMapping(
    //     value = "/{id}",
    //     produces = MediaType.IMAGE_JPEG_VALUE
    // )
    // public @ResponseBody byte[] get(@PathVariable long id) {
    //     return this.pictureRepository.findOne(id).getBytes();
    // }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> serveImage(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(this.pictureRepository.findOne(id).getBytes());
    }
}
