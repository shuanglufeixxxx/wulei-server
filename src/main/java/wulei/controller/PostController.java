package wulei.controller;


import org.springframework.web.bind.annotation.*;
import wulei.modelpublic.PostPublic;
import wulei.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/post")
class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public PostPublic get(@PathVariable String id) {
        return new PostPublic( this.postService.selectById(id) );
    }

    @GetMapping
    public List<PostPublic> getByClassify(@RequestParam String classify) {
        return Arrays.asList(postService
                .selectByClassify(classify)
                .stream()
                .map(PostPublic::new)
                .toArray(PostPublic[]::new)
        );
    }
}