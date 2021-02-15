package wulei.controller;


import org.springframework.web.bind.annotation.*;
import wulei.modelpublic.PostPublic;
import wulei.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/post")
class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{id}")
    public PostPublic get(@PathVariable long id) {
        return new PostPublic( this.postRepository.findById(id).get() );
    }
}