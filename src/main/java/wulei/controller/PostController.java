package wulei.controller;


import org.springframework.web.bind.annotation.*;
import wulei.modelpublic.PostPublic;
import wulei.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/post")
class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{id}")
    public PostPublic get(@PathVariable long id) {
        return new PostPublic( this.postRepository.findOne(id) );
    }

    @GetMapping
    public List<PostPublic> getByClassify(@RequestParam String classify) {
        return Arrays.asList(postRepository.findByClassifyFeatured(classify)
                .stream()
                .map(PostPublic::new)
                .toArray(PostPublic[]::new)
        );
    }
}