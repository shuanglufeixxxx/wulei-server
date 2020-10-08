package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wulei.repository.PictureRepository;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    PictureRepository pictureRepository;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> serveImage(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(this.pictureRepository.findOne(id).getBytes());
    }
}
