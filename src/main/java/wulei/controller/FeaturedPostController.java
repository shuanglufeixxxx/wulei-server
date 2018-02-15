package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wulei.modelpublic.PostPublic;
import wulei.service.FeaturedPostService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("featuredPost")
public class FeaturedPostController {

    @Autowired
    private FeaturedPostService featuredPostService;

    @GetMapping
    public List<PostPublic> get(@RequestParam String classify) {
        return Arrays.asList(featuredPostService
                .selectByClassify(classify)
                .stream()
                .map(PostPublic::new)
                .toArray(PostPublic[]::new)
        );
    }
}
