package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import wulei.modelpublic.PostPublic;
import wulei.repository.PostRepository;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("featuredPost")
public class FeaturedPostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<PostPublic> get(@RequestParam String classify) {
        return Arrays.asList(postRepository.findByClassifyFeatured(classify)
                .stream()
                .map(PostPublic::new)
                .toArray(PostPublic[]::new)
        );
    }
}
